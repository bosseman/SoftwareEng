package com.CardTracker.SoftwareEng.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.CardTracker.SoftwareEng.shared.dto.UserDto;
import com.CardTracker.SoftwareEng.shared.dto.CardDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDto);

	UserDto getUser(String userName);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String userId, UserDto userDto);

	List<CardDto> getFavoriteCards(String userId, int page, int limit);

	CardDto addFavoriteCard(String userId, long cardId);

	boolean deleteFavorite(String userId, long cardId);
}
