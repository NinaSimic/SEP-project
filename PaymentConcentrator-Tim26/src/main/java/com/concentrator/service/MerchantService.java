package com.concentrator.service;

import java.util.ArrayList;

import com.concentrator.model.Merchant;
import com.concentrator.model.MerchantAllowedPayment;
import com.concentrator.model.PaymentType;

public interface MerchantService {
	public String findBankUrl(String merchantId, String merchantPassword);
	public String checkMerchantData(String merchantId, String merchantPassword);
	public Merchant findMerchantByMerchantId(String merchantId);
	public ArrayList<MerchantAllowedPayment> findPaymentTypeByMerchant(Merchant merchant);
}
