package com.CardTracker.SoftwareEng.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CardTracker.SoftwareEng.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUserName(String userName);

	UserEntity findByUserId(String userId);

}
