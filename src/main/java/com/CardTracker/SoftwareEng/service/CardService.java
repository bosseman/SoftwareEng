package com.CardTracker.SoftwareEng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CardTracker.SoftwareEng.shared.dto.CardDto;


public interface CardService {

	public CardDto getCard(long id);
	
	public List<CardDto> getAllCards();

	public List<CardDto> getAllCardsLike(String searchName);
}
