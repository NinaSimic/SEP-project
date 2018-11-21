package com.concentrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrator.repository.MerchantRepository;
import com.concentrator.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Override
	public String findBankUrl(String merchantId, String merchantPassword) {
		return merchantRepository.findMerchantByMerchantIdAndMerchantPassword(merchantId, merchantPassword).getBankUrl();
	}

}
