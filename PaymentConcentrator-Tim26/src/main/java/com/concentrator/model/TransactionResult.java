package com.concentrator.model;

import java.util.Date;

public class TransactionResult extends Transaction {
	
	private String issuerOrderId;
	private String issuerTimestamp;
	private boolean result;
	
	public TransactionResult(){
		
	}
	
	public TransactionResult(String pan, char[] securityCode, String ownerName, Date validityDate,
			String amount, String acquirerOrderId, String acquirerTimestamp, String acquirerSwiftCode,String issuerOrderId, String issuerTimestamp, boolean result) {
		super(pan, securityCode, ownerName, validityDate, amount, acquirerOrderId, acquirerTimestamp, acquirerSwiftCode);
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerOrderId;
		this.result = result;
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

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}
