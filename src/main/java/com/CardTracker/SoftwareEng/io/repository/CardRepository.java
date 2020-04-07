package com.CardTracker.SoftwareEng.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CardTracker.SoftwareEng.entity.CardEntity;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {
	public CardEntity findById(long id);

	public List<CardEntity> findByCardNameContains(String cardName);
	
	public CardEntity findByCardName(String cardName);

}
