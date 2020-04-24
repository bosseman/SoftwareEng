package com.CardTracker.SoftwareEng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.RoleEntity;
import com.CardTracker.SoftwareEng.entity.UserEntity;
import com.CardTracker.SoftwareEng.io.repository.AuthorityRepository;
import com.CardTracker.SoftwareEng.io.repository.CardRepository;
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
	@Autowired
	CardRepository cr;
	
	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) throws FileNotFoundException, IOException {
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
			//Removed. 
			userRepo.save(adminUser);
		}
		
		//loadData();
	}
	private void loadData() throws FileNotFoundException, IOException {
		//File file = new File("C:/Users/bosse/playerData.csv");
		try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/bosse/playerData.csv"))){
			String line;
			while((line = br.readLine()) != null) {
				System.out.println("TEST");
				String[] fields = line.split(",");
				CardEntity ce = new CardEntity();
				ce.setName(fields[0]);
				ce.setTeam(fields[1]);
				ce.setPrice(fields[2]);
				ce.setTimeSold(fields[3]);
				ce.setPosition(fields[4]);
				ce.setAge(fields[5]);
				ce.setHeight(fields[6]);
				//No wieght
				ce.setDraftPosition(fields[8]);
				ce.setShootingPercentage(fields[9]);
				ce.setFreeThrowPercentage(fields[10]);
				ce.setUsageRate(fields[11]);
				ce.setThreePointPercentage(fields[12]);
				ce.setFreeThrowFrequency(fields[13]);
				ce.setAssistRate(fields[14]);
				ce.setTurnoverRate(fields[15]);
				ce.setReboundRate(fields[16]);
				ce.setBlockingRate(fields[17]);
				ce.setStealRate(fields[18]);
				ce.setDefenseRate(fields[19]);
				cr.save(ce);
			}
			br.close();
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
