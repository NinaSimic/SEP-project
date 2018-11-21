package com.bank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Merchant implements Serializable{
	
	private static final long serialVersionUID = -7475447117808950140L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long merId;
	
	@Column
	private String name;
	
	@Column(nullable = true)
	@Size(max = 30)
	private String merchantId;
	
	@Column(nullable = true)
	@Size(max = 100)
	private String merchantPassword;
	
	@Column
	private String address;
	
	@Column
	private String phone;
	
	@Column
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	
}
