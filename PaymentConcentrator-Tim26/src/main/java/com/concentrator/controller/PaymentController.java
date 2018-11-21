package com.concentrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.concentrator.model.PaymentRequest;
import com.concentrator.service.MerchantService;

@RestController
@RequestMapping("/pay")
public class PaymentController {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	public PaymentController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@PostMapping
	@ResponseBody
	public String pay(@RequestBody PaymentRequest paymentRequest) {
		switch (paymentRequest.getType().toString()) {
			case "CARD":
				return restTemplate.postForObject(merchantService.findBankUrl(paymentRequest.getMerchantId(), paymentRequest.getMerchantPassword()) + "/api/payment/generate", paymentRequest, String.class);
			default:
				return "";
		}
	}
	
}
