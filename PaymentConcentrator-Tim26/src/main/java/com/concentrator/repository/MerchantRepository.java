package com.concentrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concentrator.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{
	
	public Merchant findMerchantByMerchantIdAndMerchantPassword(String merchantId, String merchantPassword);
}
