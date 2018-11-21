package com.bank.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = -7082511327064373696L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column
	protected String pan;
	@Column
	protected String securityCode;
	@Column
	protected String holder;
	@Column
	protected String validityDate;
	@Column
	protected String amount;
	@Column
	protected Long paymentId;
	@Enumerated(EnumType.STRING)
	protected PaymentStatus paymentStatus;
	
	public Transaction(){
		
	}
	
	public Transaction(String pan, String securityCode, String holder, String validityDate, String amount, Long paymentId, PaymentStatus paymentStatus) {
		super();
		this.pan = pan;
		this.securityCode = securityCode;
		this.holder = holder;
		this.validityDate = validityDate;
		this.amount = amount;
		this.paymentId = paymentId;
		this.paymentStatus = paymentStatus;
	}

	
	
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}
