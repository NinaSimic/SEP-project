package com.concentrator.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrator.config.PaypalConfig;
import com.concentrator.model.PaymentRequest;
import com.concentrator.service.PaypalService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalServiceImpl implements PaypalService {

	private final PaypalConfig payPalConfig;
	
	@Autowired
	public PaypalServiceImpl(PaypalConfig payPalConfig) {
		this.payPalConfig = payPalConfig;
	}
	
	@Override
	public String getPaymentLink(PaymentRequest paymentRequest) throws PayPalRESTException {
		Payment payment = this.createPayment(
			paymentRequest.getAmount(), 
			"USD", 
			"Paypal", 
			"sale",
			"sale", 
			"/", 
			"/" );
		for(Links links : payment.getLinks()){
			if(links.getRel().equals("approval_url")){
				return links.getHref();
			}
		}
		return "/";
	}
	
	@Override
	public Map<String, Object> completePayment(HttpServletRequest req){
	    Map<String, Object> response = new HashMap();
	    Payment payment = new Payment();
	    payment.setId(req.getParameter("paymentId"));

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getParameter("PayerID"));
	    try {
	        APIContext context = new APIContext("ASFzZAyO49hoNSYTe90_1XTAQd2dK8kqQXHDJ30VY7-LsiH5Og6aOcUTC5CyTSfuaFUbNqdnkNYCuqnD", "EA_NF4t8EqFsuJ6jKE1l-d94nBAxvvcyc4jCE90WACPx4Fg989z6XHAhaLiEhA67mis12ZhuexpDG1ox", "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	            response.put("status", "success");
	            response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    return response;
	}
	
	@Override
	public Map<String, Object> createPayment(String sum){
		System.out.println("usao servbis");
	    Map<String, Object> response = new HashMap<String, Object>();
	    Amount amount = new Amount();
	    amount.setCurrency("USD");
	    amount.setTotal(sum);
	    Transaction transaction = new Transaction();
	    transaction.setAmount(amount);
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);

	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");

	    Payment payment = new Payment();
	    payment.setIntent("Sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl("https://localhost:4200");
	    redirectUrls.setReturnUrl("https://localhost:4200");
	    payment.setRedirectUrls(redirectUrls);
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext("ASFzZAyO49hoNSYTe90_1XTAQd2dK8kqQXHDJ30VY7-LsiH5Og6aOcUTC5CyTSfuaFUbNqdnkNYCuqnD", "EA_NF4t8EqFsuJ6jKE1l-d94nBAxvvcyc4jCE90WACPx4Fg989z6XHAhaLiEhA67mis12ZhuexpDG1ox", "sandbox");
	        createdPayment = payment.create(context);
	        if(createdPayment!=null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                    redirectUrl = link.getHref();
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectUrl);
	        }
	    } catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
	    return response;
	}
	
	@Override
	public Payment createPayment(double amount, String currency, String method, String purpose,
								 String description, String cancelUrl, String successUrl) throws PayPalRESTException {
		Amount paymentAmount = new Amount(currency, String.format("%.2f", amount));
		
		Transaction transaction = new Transaction();
		transaction.setDescription(description);
		transaction.setAmount(paymentAmount);
		
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		
		Payer payer = new Payer();
		payer.setPaymentMethod(method);
		
		Payment payment = new Payment(purpose, payer);
		payment.setTransactions(transactions);
		
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);
		
		return payment.create(payPalConfig.apiContext());
	}

	@Override
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		return payment.execute(payPalConfig.apiContext(), paymentExecution);
	}

}
