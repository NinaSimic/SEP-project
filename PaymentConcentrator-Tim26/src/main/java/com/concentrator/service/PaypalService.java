package com.concentrator.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.concentrator.model.PaymentRequest;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {

	String getPaymentLink(PaymentRequest paymentRequest) throws PayPalRESTException;
	
	Payment createPayment(double amount, 
						  String currency,
						  String method,
						  String purpose,
						  String description,
						  String cancelUrl,
						  String successUrl) throws PayPalRESTException;
	
	Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

	Map<String, Object> createPayment(String sum);

	Map<String, Object> completePayment(HttpServletRequest req);
	
}
