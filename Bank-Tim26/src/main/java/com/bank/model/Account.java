package com.bank.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = -3844861383793286124L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column
	private String number;

	@Column
	private boolean active;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "mer_id")
	private Merchant merId;

	@Column
	private double balance;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Merchant getMerchant() {
		return merId;
	}

	public void setMerchant(Merchant merchant) {
		this.merId = merchant;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
}
