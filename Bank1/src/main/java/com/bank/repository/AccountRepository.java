package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Account;
import com.bank.model.Merchant;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findAccountByNumber(String number);
	public Account findAccountByMerId(Merchant merId);
}
