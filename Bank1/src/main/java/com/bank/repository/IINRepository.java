package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.IIN;


public interface IINRepository  extends JpaRepository<IIN, Long> {
	
	public IIN findIINByBin(String bin);
}
