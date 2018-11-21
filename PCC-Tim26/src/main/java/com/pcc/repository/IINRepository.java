package com.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcc.model.IIN;

public interface IINRepository  extends JpaRepository<IIN, Long> {
	
	public IIN findIINByBin(String bin);
}
