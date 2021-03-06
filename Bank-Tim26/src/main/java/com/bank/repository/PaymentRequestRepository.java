package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.model.PaymentRequest;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long>{
	
	public PaymentRequest getPaymentRequestByPaymentLinkContainingAndMerchantIdAndMerchantOrderId(String paymentLink, String merchantId, String merchantOrderId);
	
	public PaymentRequest getPaymentRequestById(Long paymentId);
}
