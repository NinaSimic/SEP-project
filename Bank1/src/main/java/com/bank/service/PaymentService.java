package com.bank.service;

import com.bank.model.Transaction;
import org.json.simple.parser.ParseException;

import com.bank.model.InterBankTransaction;
import com.bank.model.PaymentRequest;

public interface PaymentService {
	public String acquirerCheckRequest(PaymentRequest paymentRequest);
	public void invalidatePaymentLink(InterBankTransaction interBankTransaction);
	public boolean checkTransaction(Long paymentId);
	public PaymentRequest checkUrl(String paymentLink, String merchantId, String merchandOrderId);
	public Transaction checkTransactionRequest(Transaction transaction) throws ParseException;
	public Transaction intraBankTransaction(Transaction transaction);
	public InterBankTransaction interBankTransaction(Transaction transaction);
	public InterBankTransaction issuerCheckRequest(InterBankTransaction interBankTransaction);
}
