package com.bank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IIN implements Serializable{

	private static final long serialVersionUID = -1299527841624736242L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column
	private String bankSwift;
	@Column
	private String bin;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankSwift() {
		return bankSwift;
	}
	public void setBankSwift(String bankSwift) {
		this.bankSwift = bankSwift;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	
	
}
