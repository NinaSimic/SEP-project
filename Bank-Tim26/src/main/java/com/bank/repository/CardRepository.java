package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	public Card findCardByPan(String pan);
}
