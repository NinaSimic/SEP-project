package com.pcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcc.model.IIN;
import com.pcc.model.InterBankTransaction;
import com.pcc.repository.IINRepository;
import com.pcc.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private IINRepository iinRepository;
	
	@Override
	public String checkRequest(InterBankTransaction interBankTransaction) {
		IIN iin = iinRepository.findIINByBin(interBankTransaction.getPan().substring(0, 6));
		return iin.getUrl();
	}

}
