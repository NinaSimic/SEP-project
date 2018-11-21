package com.bank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUri {

	
	@Value("${spring.data.pcc}")
	private String pccUri;

	@Value("${spring.data.concentrator}")
	private String concentratorUri;
	
	@Value("${spring.data.bank}")
	private String bankName;
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getConcetratorUri() {
		return concentratorUri;
	}

	public void setConcetratorUri(String concentratorUri) {
		this.concentratorUri = concentratorUri;
	}

	public String getPccUri() {
		return pccUri;
	}

	public void setPccUri(String pccUri) {
		this.pccUri = pccUri;
	}	
	
}
