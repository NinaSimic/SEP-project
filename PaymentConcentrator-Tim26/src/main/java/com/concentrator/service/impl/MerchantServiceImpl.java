package com.concentrator.service.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrator.model.Merchant;
import com.concentrator.model.MerchantAllowedPayment;
import com.concentrator.model.PaymentType;
import com.concentrator.repository.MerchantAllowedPaymentRepository;
import com.concentrator.repository.MerchantRepository;
import com.concentrator.service.MerchantService;
import com.concentrator.utils.PasswordUtil;

@Service
public class MerchantServiceImpl implements MerchantService {

	private static final Charset charset = Charset.forName("UTF-8");
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private MerchantAllowedPaymentRepository merchantAllowedPaymentRepository;
	
	@Override
	public String findBankUrl(String merchantId, String merchantPassword) {
		return merchantRepository.findMerchantByMerchantId(merchantId).getBankUrl();
	}

	@Override
	public String checkMerchantData(String merchantId, String merchantPassword) {
		Merchant merchant = merchantRepository.findMerchantByMerchantId(merchantId);
		if (merchant == null) {
			return "Authentication failed";
		}
		String verifyHash = merchant.getMerchantPassword();
		String verifyPass = merchantPassword;
		
		if(!PasswordUtil.verify(verifyHash, verifyPass.toCharArray(), charset)) {
			return  "Authentication failed";
		}
		
		return "";
	}

	@Override
	public Merchant findMerchantByMerchantId(String merchantId) {
		return merchantRepository.findMerchantByMerchantId(merchantId);
	}

	@Override
	public ArrayList<MerchantAllowedPayment> findPaymentTypeByMerchant(Merchant merchant) {
		return merchantAllowedPaymentRepository.findByMerchant(merchant);
	}

}
