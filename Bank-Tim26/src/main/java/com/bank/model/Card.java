package com.bank.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Card implements Serializable {

	private static final long serialVersionUID = -3844861383793286124L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String pan;

	@Column
	private boolean active;
	
	@Column
	private String holder;

	@Column
	private String securityCode;
	
	@Column
	private String validityDate;
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Column
	private double cardLimit;
	
	@Enumerated(EnumType.STRING)
	private CardType cardType;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType type) {
		this.cardType = type;
	}
	
}
