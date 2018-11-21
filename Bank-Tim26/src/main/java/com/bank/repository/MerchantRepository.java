package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{

	public Merchant findMerchantByMerchantId(String merchantId);
}
