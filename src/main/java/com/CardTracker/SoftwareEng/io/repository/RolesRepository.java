package com.CardTracker.SoftwareEng.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CardTracker.SoftwareEng.entity.RoleEntity;

@Repository
public interface RolesRepository extends CrudRepository<RoleEntity, Long> {
	RoleEntity findByName(String name);
}
