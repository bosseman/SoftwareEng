package com.CardTracker.SoftwareEng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CardTracker.SoftwareEng.shared.dto.PlayerStatsDTO;

public interface CardService {

	public PlayerStatsDTO getCard(long id);

	public List<PlayerStatsDTO> getAllCards();

	public List<PlayerStatsDTO> getAllCardsLike(String searchName);
}
