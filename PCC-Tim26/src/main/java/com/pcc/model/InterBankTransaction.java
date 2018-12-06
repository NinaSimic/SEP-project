package com.pcc.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "InterBankTransaction")
public class InterBankTransaction extends Transaction {
	
	private static final long serialVersionUID = 8839516770841134432L;
	
	@Column
	private String acquirerOrderId;
	@Column
	private String acquirerTimestamp;
	@Column
	private String issuerOrderId;
	@Column
	private String issuerTimestamp;
	
	public InterBankTransaction(){
		
	}
	
	public InterBankTransaction(String pan, char[] securityCode, String holder, String validityDate,
			String amount, String acquirerOrderId, String acquirerTimestamp, String acquirerSwiftCode,String issuerOrderId, String issuerTimestamp, PaymentStatus paymentStatus, Long paymentId) {
		super(pan, securityCode, holder, validityDate, amount, paymentId, paymentStatus);
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerOrderId;
	}

	public String getIssuerOrderId() {
		return issuerOrderId;
	}
	public void setIssuerOrderId(String issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}
	public String getIssuerTimestamp() {
		return issuerTimestamp;
	}
	public void setIssuerTimestamp(String issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public String getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(String acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}
	
	
	
}
