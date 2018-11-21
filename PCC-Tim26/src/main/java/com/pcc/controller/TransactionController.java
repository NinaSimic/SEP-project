package com.pcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pcc.model.InterBankTransaction;
import com.pcc.repository.InterBankTransactionRepository;
import com.pcc.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private InterBankTransactionRepository interBankTransactionRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/request")
	public InterBankTransaction sendRequest(@RequestBody InterBankTransaction interBankTransaction){
		String url = transactionService.checkRequest(interBankTransaction);
		InterBankTransaction ibr = restTemplate.postForObject(url + "/api/payment/inter", interBankTransaction, InterBankTransaction.class);
		interBankTransactionRepository.save(ibr);
		return ibr;
	}
	
}
