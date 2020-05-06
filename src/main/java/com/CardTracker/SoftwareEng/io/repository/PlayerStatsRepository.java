package com.CardTracker.SoftwareEng.io.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.playerInfo;

@Repository
public interface PlayerStatsRepository extends CrudRepository<playerInfo, Long>{
	
	public playerInfo findById(long id);

	public List<playerInfo> findByNameContains(String name);
	
	public playerInfo findByName(String name);
	
	public playerInfo findByMatchingId(String matchingId);
}
