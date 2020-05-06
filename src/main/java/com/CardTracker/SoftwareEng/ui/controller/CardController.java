package com.CardTracker.SoftwareEng.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CardTracker.SoftwareEng.shared.dto.PlayerStatsDTO;
import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.ui.response.GetCardDetailsResponseModel;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/cards")
public class CardController {
	@Autowired
	CardService cardService;

	// Returns all cards
	@GetMapping // Tested --> Good
	public List<GetCardDetailsResponseModel> getCards() {
		List<GetCardDetailsResponseModel> listOfCards = new ArrayList<GetCardDetailsResponseModel>();

		List<PlayerStatsDTO> listOfCardDto = cardService.getAllCards();

		for (PlayerStatsDTO card : listOfCardDto) {
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
		PlayerStatsDTO cardDto = cardService.getCard(cardId);
		
		BeanUtils.copyProperties(cardDto, cardDetails);
		
		
		return cardDetails;
	}

	// Search for a card
	@GetMapping("/search/{searchName}")
	public List<GetCardDetailsResponseModel> searchCards(@PathVariable(name = "searchName") String searchName) {
		List<GetCardDetailsResponseModel> listOfCards = new ArrayList<GetCardDetailsResponseModel>();

		List<PlayerStatsDTO> listCardDto = cardService.getAllCardsLike(searchName);

		for (PlayerStatsDTO cd : listCardDto) {
			GetCardDetailsResponseModel card = new GetCardDetailsResponseModel();
			BeanUtils.copyProperties(cd, card);
			
			
			listOfCards.add(card);
		}
		return listOfCards;
	}
}
