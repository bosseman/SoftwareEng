package com.CardTracker.SoftwareEng.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CardTracker.SoftwareEng.shared.dto.CardDto;
import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.ui.response.GetCardDetailsResponseModel;

@RestController
@RequestMapping("/cards")
public class CardController {
	@Autowired
	CardService cardService;
	
	@GetMapping //Tested --> Good
	public List<GetCardDetailsResponseModel> getCards() {
		List<GetCardDetailsResponseModel> listOfCards = new ArrayList<GetCardDetailsResponseModel>();
		
		List<CardDto> listOfCardDto = cardService.getAllCards();

		for(CardDto card : listOfCardDto) {
			GetCardDetailsResponseModel responseCard = new GetCardDetailsResponseModel();
			BeanUtils.copyProperties(card, responseCard);
			listOfCards.add(responseCard);
		}

		return listOfCards;
	}
	

}
