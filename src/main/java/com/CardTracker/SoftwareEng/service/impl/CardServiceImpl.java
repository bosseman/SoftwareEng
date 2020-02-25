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

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	@Override
	public CardDto getCard(long id) {
		CardDto cardDto = new CardDto();
		
		CardEntity cardEntity = cardRepository.findById(id);
		BeanUtils.copyProperties(cardEntity, cardDto);

		
		return cardDto;
	}

	public List<CardDto> getAllCards() {
		List<CardDto> listOfCards = new ArrayList<>();
		
		List<CardEntity> EntityCards = (List<CardEntity>) cardRepository.findAll();

		for(CardEntity card: EntityCards) {
			CardDto addThis = new CardDto();
			BeanUtils.copyProperties(card, addThis);
			listOfCards.add(addThis);
		}
		
		return listOfCards;
	}
	
	

}
