package com.CardTracker.SoftwareEng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.playerInfo;
import com.CardTracker.SoftwareEng.io.repository.CardRepository;
import com.CardTracker.SoftwareEng.io.repository.PlayerStatsRepository;
import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.shared.dto.PlayerStatsDTO;

/*
 * Services offered for users not logged in
 */

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	PlayerStatsRepository playerStatsRepository;

	@Autowired
	CardRepository c;

	// Returns a single card by its ID
	@Override
	public PlayerStatsDTO getCard(long id) {
		PlayerStatsDTO cardDto = new PlayerStatsDTO();

		playerInfo cardEntity = playerStatsRepository.findById(id);

		// System.out.println(cardEntity.getCardDetails().size());
		// System.out.println(cardEntity.getPlayerId());
		List<CardEntity> cc = c.findByPlayer(cardEntity.getPlayerId());

		BeanUtils.copyProperties(cardEntity, cardDto);

		if (!cc.isEmpty()) {
			for (CardEntity cardy : cc) {
				cardDto.setCardDetails(cardy);
			}
		}

		return cardDto;
	}

	// Will return all cards saved in DB
	public List<PlayerStatsDTO> getAllCards() {
		List<PlayerStatsDTO> listOfCards = new ArrayList<>();

		List<playerInfo> entityCards = (List<playerInfo>) playerStatsRepository.findAll();

		for (playerInfo card : entityCards) {
			PlayerStatsDTO addThis = new PlayerStatsDTO();
			BeanUtils.copyProperties(card, addThis);
			
			List<CardEntity> cc = c.findByPlayer(addThis.getPlayerId());
			
			if (!cc.isEmpty()) {
				for (CardEntity cardy : cc) {
					addThis.setCardDetails(cardy);
				}
			}
			
			listOfCards.add(addThis);
		}

		return listOfCards;
	}

	// Search method. Will look for anything that contains the name passed by(or
	// character)
	public List<PlayerStatsDTO> getAllCardsLike(String searchName) {
		List<PlayerStatsDTO> listOfCards = new ArrayList<PlayerStatsDTO>();

		List<playerInfo> cardEntity = playerStatsRepository.findByNameContains(searchName);

		for (playerInfo ce : cardEntity) {
			PlayerStatsDTO card = new PlayerStatsDTO();
			BeanUtils.copyProperties(ce, card);
			
			
			List<CardEntity> cc = c.findByPlayer(card.getPlayerId());
			
			if (!cc.isEmpty()) {
				for (CardEntity cardy : cc) {
					card.setCardDetails(cardy);
				}
			}
			
			listOfCards.add(card);
		}

		return listOfCards;
	}

}
