package com.CardTracker.SoftwareEng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.CardTracker.SoftwareEng.entity.AuthorityEntity;
import com.CardTracker.SoftwareEng.entity.RoleEntity;
import com.CardTracker.SoftwareEng.entity.UserEntity;
import com.CardTracker.SoftwareEng.io.repository.AuthorityRepository;
import com.CardTracker.SoftwareEng.io.repository.RolesRepository;
import com.CardTracker.SoftwareEng.io.repository.UserRepository;
import com.CardTracker.SoftwareEng.shared.Utils;

@Component
public class InitialUsersSetup {
	@Autowired
	AuthorityRepository authorityRepo;

	@Autowired
	RolesRepository roleRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

	@Autowired
	UserRepository userRepo;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		AuthorityEntity delete = createAuthority("DELETE_AUTH");
		AuthorityEntity write = createAuthority("WRITE_AUTH");
		AuthorityEntity scrap = createAuthority("SCRAP_AUTH");
		AuthorityEntity read = createAuthority("READ_AUTH");

		RoleEntity admin = createRole("ROLE_ADMIN", Arrays.asList(delete, write, scrap, read)); // developers
		RoleEntity user = createRole("ROLE_USER", Arrays.asList(read)); // Any user

		if (admin == null)
			return;

		UserEntity adminUser = new UserEntity();
		if (userRepo.findByUserName("admin") == null) {
			adminUser.setUserName("admin");
			adminUser.setEmail("JustBecasueINeedThis.com");
			adminUser.setUserId(utils.generateUserId(15));
			adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("CatsAreTheBest123!"));
			adminUser.setRoles(Arrays.asList(admin));

			userRepo.save(adminUser);
		}
	}

	private AuthorityEntity createAuthority(String name) {

		AuthorityEntity authority = authorityRepo.findByName(name);

		if (authority == null) {
			authority = new AuthorityEntity(name);
			authorityRepo.save(authority);
		}
		return authority;
	}

	private RoleEntity createRole(String name, List<AuthorityEntity> authorities) {

		RoleEntity role = roleRepo.findByName(name);
		if (role == null) {
			role = new RoleEntity(name);
			role.setAuthorityRoles(authorities);
			roleRepo.save(role);
		}
		return role;
	}
}
