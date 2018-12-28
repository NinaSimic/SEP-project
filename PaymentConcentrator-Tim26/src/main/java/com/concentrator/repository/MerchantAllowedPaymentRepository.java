package com.concentrator.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.concentrator.model.Merchant;
import com.concentrator.model.MerchantAllowedPayment;
import com.concentrator.model.PaymentType;


@Repository
public interface MerchantAllowedPaymentRepository extends JpaRepository<MerchantAllowedPayment, Long>{
	
	public ArrayList<MerchantAllowedPayment> findByMerchant(Merchant merchant);
	
}
