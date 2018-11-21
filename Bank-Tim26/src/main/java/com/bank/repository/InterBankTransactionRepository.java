package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.InterBankTransaction;


@Repository
public interface InterBankTransactionRepository extends JpaRepository<InterBankTransaction, Long> {
	public InterBankTransaction findTransactionResultByPaymentId(Long paymentId);
}
