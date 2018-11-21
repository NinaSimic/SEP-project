package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long>{
	
	public PaymentRequest getPaymentRequestByPaymentLinkContainingAndMerchantIdAndMerchantOrderId(String paymentLink, String merchantId, String merchantOrderId);
	
	public PaymentRequest getPaymentRequestById(Long paymentId);
}
