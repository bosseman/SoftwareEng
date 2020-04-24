package com.CardTracker.SoftwareEng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.io.repository.CardRepository;

import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.shared.dto.CardDto;

/*
 * Services offered for users not logged in
 */

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	CardRepository cardRepository;

	// Returns a single card by its ID
	@Override
	public CardDto getCard(long id) {
		CardDto cardDto = new CardDto();

		CardEntity cardEntity = cardRepository.findById(id);
		BeanUtils.copyProperties(cardEntity, cardDto);

		return cardDto;
	}

	// Will return all cards saved in DB
	public List<CardDto> getAllCards() {
		List<CardDto> listOfCards = new ArrayList<>();

		List<CardEntity> entityCards = (List<CardEntity>) cardRepository.findAll();

		for (CardEntity card : entityCards) {
			CardDto addThis = new CardDto();
			BeanUtils.copyProperties(card, addThis);
			listOfCards.add(addThis);
		}

		return listOfCards;
	}

	// Search method. Will look for anything that contains the name passed by(or
	// character)
	public List<CardDto> getAllCardsLike(String searchName) {
		List<CardDto> listOfCards = new ArrayList<CardDto>();

		List<CardEntity> cardEntity = cardRepository.findByNameContains(searchName);

		for (CardEntity ce : cardEntity) {
			CardDto card = new CardDto();
			BeanUtils.copyProperties(ce, card);

			listOfCards.add(card);
		}

		return listOfCards;
	}

}
