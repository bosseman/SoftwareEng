package com.CardTracker.SoftwareEng.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.playerInfo;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {
	public CardEntity findById(long id);

	public List<CardEntity> findByNameContains(String name);
	
	public CardEntity findByName(String name);
	
	public List<CardEntity> findByPlayer(long player);
	

}
