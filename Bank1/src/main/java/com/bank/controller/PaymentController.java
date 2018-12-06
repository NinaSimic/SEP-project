package com.bank.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.InterBankTransaction;
import com.bank.model.PaymentRequest;
import com.bank.model.Transaction;
import com.bank.service.impl.PaymentServiceImpl;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("/api/payment")
public class PaymentController {
	
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	@PostMapping("/generate")
	@ResponseBody
	public String generatePaymentLink(@RequestBody PaymentRequest paymentRequest){
		String ret = paymentServiceImpl.acquirerCheckRequest(paymentRequest);
		return ret;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/check/url", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PaymentRequest checkUrl(@RequestParam("paymentUrl") String paymentUrl, @RequestParam("merchantId") String merchantId, @RequestParam("merchantOrderId") String merchantOrderId){
		return paymentServiceImpl.checkUrl(paymentUrl, merchantId, merchantOrderId);
	}
	
	//acquirer salje podatke pcc-u
	@PostMapping("/transaction")
	@ResponseBody
	public Transaction sendData(@RequestBody Transaction transaction) throws ParseException{
		Transaction tr= paymentServiceImpl.checkTransactionRequest(transaction);
		return tr;
	}
	
	//issuer prima transakciju od pcc-a(koji ga je nasao), proverava da li je u redu i vraca rezultat transakcije pcc-u
	@PostMapping("/inter")
	public InterBankTransaction issuerCheckRequest(@RequestBody InterBankTransaction interBankTransaction){
		InterBankTransaction ibt = paymentServiceImpl.issuerCheckRequest(interBankTransaction);
		return ibt;
	}
	
	/*@PostMapping("/saveData")
	@ResponseBody
	public void saveData(@RequestBody TransactionResult transactionResult){
		paymentServiceImpl.invalidatePaymentLink(transactionResult);
		if(transactionResult.isResult()){
			restTemplate.postForObject(uri.getConcetratorUri() + "/pay/success/"+transactionResult.getPaymentId(), transactionResult, Void.class);
			return;
		}
		restTemplate.postForObject(uri.getConcetratorUri() + "/pay/cancel/"+transactionResult.getPaymentId(), transactionResult, Void.class);
	}
	*/
	@RequestMapping(method=RequestMethod.GET, path="/test", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean test(@RequestParam("paymentId") long paymentId){
		return paymentServiceImpl.checkTransaction(paymentId);
	}

}
