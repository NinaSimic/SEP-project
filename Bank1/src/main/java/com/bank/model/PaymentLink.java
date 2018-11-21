package com.bank.model;

import javax.validation.constraints.Size;

public class PaymentLink {

	@Size(max = 30)
	private String paymentUrl;
	
	@Size (max = 10)
	private Long paymentId;

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
}
