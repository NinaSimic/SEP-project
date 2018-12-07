package com.bank.controller;

import java.io.IOException;

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
import com.bank.model.PaymentRequest;
import com.bank.model.Transaction;
import com.bank.service.impl.PaymentServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {
	
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	@PostMapping("/generate")
	@ResponseBody
	public String generatePaymentLink(@RequestBody PaymentRequest paymentRequest) throws IOException{
		String ret = paymentServiceImpl.acquirerCheckRequest(paymentRequest);
		return ret;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/check/url", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PaymentRequest checkUrl(@RequestParam("paymentUrl") String paymentUrl, @RequestParam("merchantId") String merchantId, @RequestParam("merchantOrderId") String merchantOrderId){
		System.out.println("usao check");
		return paymentServiceImpl.checkUrl(paymentUrl, merchantId, merchantOrderId);
	}
	
	@PostMapping("/transaction")
	@ResponseBody
	public Transaction sendData(@RequestBody Transaction transaction) throws ParseException{
		Transaction tr= paymentServiceImpl.checkTransactionRequest(transaction);
		return tr;
	}

}
