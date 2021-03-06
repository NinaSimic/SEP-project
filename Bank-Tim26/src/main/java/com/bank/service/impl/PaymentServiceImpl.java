package com.bank.service.impl;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.config.DatabaseUri;
import com.bank.model.Account;
import com.bank.model.Card;
import com.bank.model.CardType;
import com.bank.model.IIN;
import com.bank.model.InterBankTransaction;
import com.bank.model.Merchant;
import com.bank.model.Transaction;
import com.bank.model.PaymentRequest;
import com.bank.model.PaymentStatus;
import com.bank.repository.AccountRepository;
import com.bank.repository.CardRepository;
import com.bank.repository.IINRepository;
import com.bank.repository.MerchantRepository;
import com.bank.repository.TransactionRepository;
import com.bank.repository.InterBankTransactionRepository;
import com.bank.repository.PaymentRequestRepository;
import com.bank.service.PaymentService;
import com.bank.utils.PasswordUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DatabaseUri uri;
	
	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private PaymentRequestRepository paymentRequestRepository;
	
	@Autowired
	private InterBankTransactionRepository interBankTransactionRepository;
	
	@Autowired
	private IINRepository iinRepository;
	
	@Override
	public String acquirerCheckRequest(PaymentRequest paymentRequest) throws IOException {
		System.out.println("usao");
		if (!checkMerchantData(paymentRequest.getMerchantId(), paymentRequest.getMerchantPassword()).equals("")) {
			String url = generatePaymentRequestUrl(paymentRequest.getMerchantId(),paymentRequest.getMerchantOrderId());
			
			paymentRequest.setPaymentLink(url);
			paymentRequest.setActive(true);
			paymentRequestRepository.save(paymentRequest);
			Runtime.getRuntime().exec(new String[]{"cmd", "/c","start vivaldi.exe "+ url});
			return url;
		} else {
			return "";
		}
	}
	
	private String generatePaymentRequestUrl(String merchantId,String merchantOrderId){		
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String numbers = "1234567890";
        
        String characters = upper + lower + numbers;
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = random.nextInt(100);
        
        while(builder.length() < length){
        	int index = (int) (random.nextFloat() * characters.length());
        	builder.append(characters.charAt(index));
        }
        
        String ret = "https://localhost:4200/payment/" + builder.toString() + "/" + merchantId + "/" + merchantOrderId;
		
		return ret;
	}
	
	@Override
	public PaymentRequest checkUrl(String paymentLink, String merchantId, String merchandOrderId) {
		PaymentRequest paymentRequest = paymentRequestRepository.getPaymentRequestByPaymentLinkContainingAndMerchantIdAndMerchantOrderId(paymentLink, merchantId, merchandOrderId);
		System.out.println("url " + paymentRequest.getPaymentLink());
		if(paymentRequest != null && paymentRequest.isActive()) {
			System.out.println("dobar");
			return paymentRequest;
		}
		return null;
	}
	
	@Override
	public Transaction checkTransactionRequest(Transaction transaction) throws ParseException {
		invalidatePaymentLink(transaction);
		IIN iin = iinRepository.findIINByBin(transaction.getPan().substring(0, 6));
		if (iin.getBankSwift().equals(uri.getBankName())) {
			Card card = cardRepository.findCardByPan(transaction.getPan());
			if (card==null) {
				return null;
			}
			if(!PasswordUtil.verify(card.getSecurityCode(), transaction.getSecurityCode(), Charset.forName("UTF-8"))) {
				return  null;
			}
			
			if (!(card.getHolder().equals(transaction.getHolder()) && card.getValidityDate().toString().substring(0, 7).equals(transaction.getValidityDate()))) {
				return null;
			}
			return intraBankTransaction(transaction);
		} else {
			InterBankTransaction interBankTransaction = interBankTransaction(transactionRepository.save(transaction));
			interBankTransactionRepository.save(interBankTransaction);
			return interBankTransaction;
		}
	}
	
	@Override
	public Transaction intraBankTransaction(Transaction transaction) {
		Card card = cardRepository.findCardByPan(transaction.getPan());
		Account account = card.getAccount();
		PaymentRequest paymentRequest = paymentRequestRepository.findOne(transaction.getPaymentId());
		if (card.getCardType().equals(CardType.DEBIT)) {
			if(account.getBalance()-Double.parseDouble(transaction.getAmount())>0) {
				account.setBalance(account.getBalance()-Double.parseDouble(transaction.getAmount()));
				Merchant merchant = merchantRepository.findMerchantByMerchantId(paymentRequest.getMerchantId());
				Account merAccount = accountRepository.findAccountByMerId(merchant);
				merAccount.setBalance(merAccount.getBalance()+Double.parseDouble(transaction.getAmount()));
				transaction.setPaymentStatus(PaymentStatus.SUCCESS);
			} else {
				transaction.setPaymentStatus(PaymentStatus.DECLINED);
			}
		} else {
			if(account.getBalance()+card.getCardLimit()-Double.parseDouble(transaction.getAmount())>0) {
				account.setBalance(account.getBalance()-Double.parseDouble(transaction.getAmount()));
				Merchant merchant = merchantRepository.findMerchantByMerchantId(paymentRequest.getMerchantId());
				Account merAccount = accountRepository.findAccountByMerId(merchant);
				merAccount.setBalance(merAccount.getBalance()+Double.parseDouble(transaction.getAmount()));
				transaction.setPaymentStatus(PaymentStatus.SUCCESS);
			} else {
				transaction.setPaymentStatus(PaymentStatus.DECLINED);
			}
		}
		transactionRepository.save(transaction);
		return transaction;
	}
	
	@Override
	public InterBankTransaction interBankTransaction(Transaction transaction) {
		InterBankTransaction interBankTransaction = new InterBankTransaction(transaction.getPan(), transaction.getSecurityCode(), transaction.getHolder(),
				transaction.getValidityDate(), transaction.getAmount(), transaction.getId().toString(),
				(new Date()).toString(), null, null, null, transaction.getPaymentId());

		
		InterBankTransaction returnInterBankTransaction = restTemplate.postForObject(uri.getPccUri() + "/transaction/request", interBankTransaction, InterBankTransaction.class);
		if(returnInterBankTransaction.getPaymentStatus().equals(PaymentStatus.SUCCESS)) {
			PaymentRequest paymentRequest = paymentRequestRepository.findOne(interBankTransaction.getPaymentId());
			Merchant merchant = merchantRepository.findMerchantByMerchantId(paymentRequest.getMerchantId());
			Account merAccount = accountRepository.findAccountByMerId(merchant);
			merAccount.setBalance(merAccount.getBalance()+Double.parseDouble(transaction.getAmount()));
		}
		return returnInterBankTransaction;
	}

	@Override
	public void invalidatePaymentLink(Transaction transaction) {
		PaymentRequest paymentRequest = paymentRequestRepository.getPaymentRequestById(transaction.getPaymentId());
		paymentRequest.setActive(false);
		paymentRequestRepository.save(paymentRequest);
	}
	
	@Override
	public String checkMerchantData(String merchantId, String merchantPassword) {
		Merchant merchant = merchantRepository.findMerchantByMerchantId(merchantId);
		if (merchant == null) {
			return "Authentication failed";
		}
		String verifyHash = merchant.getMerchantPassword();
		String verifyPass = merchantPassword;
		
		if(!PasswordUtil.verify(verifyHash, verifyPass.toCharArray(), Charset.forName("UTF-8"))) {
			return  "Authentication failed";
		}
		
		return "";
	}
	
}