package com.concentrator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.concentrator.model.Merchant;
import com.concentrator.model.MerchantAllowedPayment;
import com.concentrator.model.PaymentRequest;
import com.concentrator.model.PaymentStatus;
import com.concentrator.model.PaymentType;
import com.concentrator.repository.PaymentRequestRepository;
import com.concentrator.service.MerchantService;
import com.concentrator.service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/pay")
public class PaymentController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaypalService paypalService;
	
	@Autowired
	private PaymentRequestRepository paymentRequestRepository;
	
	@Autowired
	public PaymentController() {
	}
	
	@PostMapping()
	@ResponseBody
	public PaymentRequest pay(HttpServletRequest request, @RequestBody PaymentRequest paymentRequest) throws PayPalRESTException {
		String check = merchantService.checkMerchantData(paymentRequest.getMerchantId(), paymentRequest.getMerchantPassword());
		Merchant merchant = merchantService.findMerchantByMerchantId(paymentRequest.getMerchantId());
		if (!check.equals("")) {
			paymentRequest.setStatus(PaymentStatus.UNAUTHORIZED);
			//paymentRequest.setUrl(merchant.getErrorUrl());
			paymentRequestRepository.save(paymentRequest);
			return paymentRequest;
		}
		ArrayList<PaymentType> types = new ArrayList<PaymentType>();
		ArrayList<MerchantAllowedPayment> allowedTypes = merchantService.findPaymentTypeByMerchant(merchant);
		for (MerchantAllowedPayment type : allowedTypes) {
			types.add(type.getPaymentType());
		}
		System.out.println("type "+types.get(0));
		System.out.println("tipovi" + PaymentType.valueOf(paymentRequest.getType().toString()));
		System.out.println("tipovi "+types.get(1));
		if(!types.contains(paymentRequest.getType())) {
			paymentRequest.setStatus(PaymentStatus.TYPE_UNAVAILABLE);
			//.setUrl(merchant.getErrorUrl());
			paymentRequestRepository.save(paymentRequest);
			return paymentRequest;
		}
		switch (paymentRequest.getType().toString()) {
			case "CARD":
				System.out.println(merchantService.findBankUrl(paymentRequest.getMerchantId(), paymentRequest.getMerchantPassword()));
				String paymentLink = restTemplate.postForObject(merchantService.findBankUrl(paymentRequest.getMerchantId(), paymentRequest.getMerchantPassword()) + "/api/payment/generate", paymentRequest, String.class);
				System.out.println(paymentLink);
				return null;
			case "PAYPAL":
				//String res = paypalService.getPaymentLink(paymentRequest);
				System.out.println("usao kontr");
				Map<String, Object> map = paypalService.createPayment(String.valueOf(paymentRequest.getAmount()));
				System.out.println("paypal " + map.get("redirect_url"));
				return null;
			default:
				return null;
		}
	}
	
	@GetMapping(value = "paypal")
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
	          //  response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    return response;
	}
	
	
}
