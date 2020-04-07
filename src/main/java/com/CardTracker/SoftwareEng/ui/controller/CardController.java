package com.CardTracker.SoftwareEng.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CardTracker.SoftwareEng.shared.dto.CardDto;
import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.ui.response.GetCardDetailsResponseModel;

@RestController
@RequestMapping("/api/cards")
public class CardController {
	@Autowired
	CardService cardService;

	// Returns all cards
	@GetMapping // Tested --> Good
	public List<GetCardDetailsResponseModel> getCards() {
		List<GetCardDetailsResponseModel> listOfCards = new ArrayList<GetCardDetailsResponseModel>();

		List<CardDto> listOfCardDto = cardService.getAllCards();

		for (CardDto card : listOfCardDto) {
			GetCardDetailsResponseModel responseCard = new GetCardDetailsResponseModel();
			BeanUtils.copyProperties(card, responseCard);
			listOfCards.add(responseCard);
		}

		return listOfCards;
	}

	// Returns a single card by its id
	@GetMapping(path = "/{cardId}")
	public GetCardDetailsResponseModel getCard(@PathVariable long cardId) {
		GetCardDetailsResponseModel cardDetails = new GetCardDetailsResponseModel();
		CardDto cardDto = cardService.getCard(cardId);

		BeanUtils.copyProperties(cardDto, cardDetails);

		return cardDetails;
	}

	// Search for a card
	@GetMapping("/search/{searchName}")
	public List<GetCardDetailsResponseModel> searchCards(@PathVariable(name = "searchName") String searchName) {
		List<GetCardDetailsResponseModel> listOfCards = new ArrayList<GetCardDetailsResponseModel>();

		List<CardDto> listCardDto = cardService.getAllCardsLike(searchName);

		for (CardDto cd : listCardDto) {
			GetCardDetailsResponseModel card = new GetCardDetailsResponseModel();
			BeanUtils.copyProperties(cd, card);
			listOfCards.add(card);
		}
		return listOfCards;
	}
}
