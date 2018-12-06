package com.concentrator.model;

import java.util.Date;

public class Transaction {
	
	private Long id;
	
	protected String pan;
	protected char[] securityCode;
	protected String holder;
	protected Date validityDate;
	protected String amount;
	protected String acquirerOrderId;
	protected String acquirerTimestamp;
	protected String acquirerSwiftCode;
	
	public Transaction(){
		
	}
	
	public Transaction(String pan, char[] securityCode, String holder, Date validityDate, String amount,
			String acquirerOrderId, String acquirerTimestamp, String acquirerSwiftCode) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.holder = holder;
		this.validityDate = validityDate;
		this.amount = amount;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.acquirerSwiftCode = acquirerSwiftCode;
	}
	
	public String getAcquirerSwiftCode() {
		return acquirerSwiftCode;
	}

	public void setAcquirerSwiftCode(String acquirerSwiftCode) {
		this.acquirerSwiftCode = acquirerSwiftCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPan() {
		return pan;
	}
	
	public void setPan(String pan) {
		this.pan = pan;
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

	public char[] getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(char[] securityCode) {
		this.securityCode = securityCode;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
