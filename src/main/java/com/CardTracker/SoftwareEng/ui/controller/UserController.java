package com.CardTracker.SoftwareEng.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.service.UserService;
import com.CardTracker.SoftwareEng.shared.dto.CardDto;
import com.CardTracker.SoftwareEng.shared.dto.UserDto;
import com.CardTracker.SoftwareEng.ui.request.UserLoginRequestModel;
import com.CardTracker.SoftwareEng.ui.request.CreateUserRequestModel;
import com.CardTracker.SoftwareEng.ui.request.UpdateUserRequestModel;
import com.CardTracker.SoftwareEng.ui.response.CreateUserResponseModel;
import com.CardTracker.SoftwareEng.ui.response.DeleteCardResponseModel;
import com.CardTracker.SoftwareEng.ui.response.GetUserResponseModel;
import com.CardTracker.SoftwareEng.ui.response.UserLoginResponseModel;
import com.CardTracker.SoftwareEng.ui.response.GetCardDetailsResponseModel;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	// -------------Below are general user functions ----------------------

	// Tested --> Good
	@GetMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public GetUserResponseModel getUser(@PathVariable String userId) {
		GetUserResponseModel returnValue = new GetUserResponseModel();
		UserDto userDto = userService.getUserByUserId(userId);

		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	// Tested --> Good
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE } // Need to config XML template
	)
	public CreateUserResponseModel createUser(@RequestBody CreateUserRequestModel userForm) {
		CreateUserResponseModel responseModel = new CreateUserResponseModel();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userForm, userDto);

		UserDto newUser = userService.createUser(userDto);
		if (newUser == null) {
			return null;
		}

		BeanUtils.copyProperties(newUser, responseModel);

		return responseModel;
	}

	// Tested --> Good
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE } // Need to config XML
																								// template
	)
	public CreateUserResponseModel updateUser(@PathVariable String id, @RequestBody UpdateUserRequestModel userForm) {
		CreateUserResponseModel updateUserResponseModel = new CreateUserResponseModel();
		UserDto newUserData = new UserDto();

		BeanUtils.copyProperties(userForm, newUserData);
		UserDto userDto = userService.updateUser(id, newUserData);

		BeanUtils.copyProperties(userDto, updateUserResponseModel);

		return updateUserResponseModel;
	}

	// ------------Below only apply to those logged in------------

	// Tested --> Good
	@GetMapping("/{userId}/favorites")
	public List<GetCardDetailsResponseModel> getFavorites(@PathVariable String userId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		List<GetCardDetailsResponseModel> listOfCards = new ArrayList<>();

		List<CardDto> cards = userService.getFavoriteCards(userId, page, limit);

		for (CardDto card : cards) {
			GetCardDetailsResponseModel cardModel = new GetCardDetailsResponseModel();
			BeanUtils.copyProperties(card, cardModel);
			listOfCards.add(cardModel);
			System.out.println(card);
		}

		return listOfCards;
	}

	// Tested ---> Good
	@PostMapping("/{userId}/favorites/{cardId}")
	public GetCardDetailsResponseModel addFavorites(@PathVariable("userId") String userId,
			@PathVariable("cardId") long cardId) {
		GetCardDetailsResponseModel cardModel = new GetCardDetailsResponseModel();

		CardDto cardDto = userService.addFavoriteCard(userId, cardId);
		BeanUtils.copyProperties(cardDto, cardModel);

		return cardModel;
	}

	// Tested --> good
	@DeleteMapping("/{userId}/favorites/{cardId}")
	public DeleteCardResponseModel deleteFavorites(@PathVariable(name = "userId") String userId,
			@PathVariable(name = "cardId") long cardId) {
		DeleteCardResponseModel deleteCardResponse = new DeleteCardResponseModel();

		boolean response = userService.deleteFavorite(userId, cardId);

		if (!response) {
			return null; // Error
		}
		deleteCardResponse.setSuccessfulDelete(response);

		return deleteCardResponse;
	}
	// --------------------------------------------------------------
}
