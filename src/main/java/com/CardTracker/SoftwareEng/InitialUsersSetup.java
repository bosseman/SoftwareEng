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
import com.CardTracker.SoftwareEng.entity.playerInfo;
import com.CardTracker.SoftwareEng.io.repository.AuthorityRepository;
import com.CardTracker.SoftwareEng.io.repository.CardRepository;
import com.CardTracker.SoftwareEng.io.repository.PlayerStatsRepository;
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
	PlayerStatsRepository cr;
	
	@Autowired
	CardRepository card;
	
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
			adminUser.setUserName("admin");
			adminUser.setEmail("JustBecasueINeedThis.com");
			adminUser.setUserId(utils.generateUserId(15));
			adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("CatsAreTheBest123!"));
			adminUser.setRoles(Arrays.asList(admin));

			userRepo.save(adminUser);
		}
		
		//loadData();
		//loadDataPart2();
	}
	/*
	private void loadDataPart2() throws FileNotFoundException, IOException{

		try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/bosse/cardList.csv"))){
			String line;
			while((line = br.readLine()) != null) {
				
				String[] fields = line.split(",");
				
				CardEntity pi = new CardEntity();
				pi.setName(fields[2]);
				pi.setPrice(fields[3]);
				pi.setTimeSold(fields[4]);
				String search = fields[1].stripLeading();
				System.out.println(search);
				playerInfo p = cr.findByMatchingId(search);
				//System.out.println(p.getPlayerId());
				pi.setPlayerID(p);
				
				//System.out.println(pi.getPlayerID().getMatchingId());
				//System.out.println(pi.getName());
				
				card.save(pi);
				

			}
			br.close();
		}
	}
	*/
	/*
	private void loadData() throws FileNotFoundException, IOException {

		try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/bosse/playerList.csv"))){
			String line;
			while((line = br.readLine()) != null) {
				System.out.println("TEST");
				String[] fields = line.split(",");
				playerInfo ce = new playerInfo();
				ce.setName(fields[0]);
				ce.setTeam(fields[1]);
				ce.setPosition(fields[2]);
				ce.setAge(fields[3]);
				ce.setHeight(fields[4]);
				ce.setWeight(fields[5]);
				ce.setDraftPosition(fields[6]);
				//No wieght
				ce.setShootingPer(fields[7]);
				ce.setFreeThrowPer(fields[8]);
				ce.setUsageRate(fields[9]);
				ce.setThreePointPer(fields[10]);
				ce.setFreeThrowFreq(fields[11]);
				ce.setAssistPer(fields[12]);
				ce.setTurnoverPer(fields[13]);
				ce.setReboundPer(fields[14]);
				ce.setBlockPer(fields[15]);
				ce.setStealPer(fields[16]);
				ce.setMatchingId(fields[17]);
				cr.save(ce);
			}
			br.close();
		}
	}
	*/
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
