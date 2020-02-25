package com.CardTracker.SoftwareEng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.UserEntity;

import com.CardTracker.SoftwareEng.io.repository.UserRepository;
import com.CardTracker.SoftwareEng.service.CardService;
import com.CardTracker.SoftwareEng.service.UserService;
import com.CardTracker.SoftwareEng.shared.dto.CardDto;
import com.CardTracker.SoftwareEng.shared.dto.UserDto;
import com.CardTracker.SoftwareEng.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder encrypt;
	@Autowired
	Utils utils;
	@Autowired
	CardService cardService;

	@Override
	public UserDto createUser(UserDto userDto) {
		// -----------------Validate username---------------
		UserEntity validateUserName = userRepository.findByUserName(userDto.getUserName());
		if (validateUserName != null) {
			return null;
		}
		// ------------------------------------------------
		// ------------Save user--------------------------
		UserDto returnDto = new UserDto();
		UserEntity userEntity = new UserEntity();

		BeanUtils.copyProperties(userDto, userEntity);

		userEntity.setUserId(utils.generateUserId(15)); // Generate public user id of 15 char
		userEntity.setEncryptedPassword(encrypt.encode(userDto.getPassword())); // Encrypt password

		UserEntity newUserEntity = userRepository.save(userEntity); // Save user

		BeanUtils.copyProperties(newUserEntity, returnDto);

		return returnDto;
		// -----------------------------------------------------
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// -------------This function is only used by Spring Security---------------
		UserEntity userEntity = userRepository.findByUserName(userName);

		if (userEntity == null)
			throw new UsernameNotFoundException(userName);

		return new User(userEntity.getUserName(), userEntity.getEncryptedPassword(), new ArrayList<>());
		// -------------------------------------------------------------------------------
	}

	@Override
	public UserDto getUser(String userName) {
		// ---------------Used to get the user------------------
		UserDto returnValue = new UserDto();

		UserEntity returnedEntity = userRepository.findByUserName(userName);

		if (returnedEntity == null)
			throw new UsernameNotFoundException(userName);
		BeanUtils.copyProperties(returnedEntity, returnValue);

		return returnValue;
		// ---------------------------------------------------------
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto();

		UserEntity userEntity = userRepository.findByUserId(userId);
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (!userDto.getUserName().toString().equals(userEntity.getUserName().toString())) {
			// User has updated the username. Validate new username
			UserEntity validateEntity = userRepository.findByUserName(userDto.getUserName());

			if (validateEntity == null) {
				// new user name is valid-->update
				userEntity.setUserName(userDto.getUserName());
			} else {
				// Username already in use
				return null;
			}
		}
		// validate Email, for now we will only check for null. Future --> validate
		// email exist
		if (userDto.getEmail() == null) {
			// Must have email.
			return null;

		}

		userEntity.setEmail(userDto.getEmail());
		UserEntity newData = userRepository.save(userEntity);

		BeanUtils.copyProperties(newData, returnValue);

		return returnValue;
	}

	@Override
	public List<CardDto> getFavoriteCards(String userId, int page, int limit) {
		List<CardDto> favoriteCards = new ArrayList<>();

		UserEntity userProfile = userRepository.findByUserId(userId);
		for (CardEntity card : userProfile.getFavorites()) {
			
			CardDto cardData = new CardDto();
			
			BeanUtils.copyProperties(card, cardData);
			cardData.setFavorite(true);
			
			favoriteCards.add(cardData);
		}

		return favoriteCards;
	}

	@Override
	public CardDto addFavoriteCard(String userId, long cardId) {
		CardDto cardDto = new CardDto();
		UserDto userDto = new UserDto();

		UserEntity userEntity = userRepository.findByUserId(userId);
		BeanUtils.copyProperties(userEntity, userDto);

		cardDto = cardService.getCard(cardId);
		CardEntity cardEntity = new CardEntity();
		BeanUtils.copyProperties(cardDto, cardEntity);

		userEntity.setFavorites(cardEntity);
		userRepository.save(userEntity);

		cardDto.setFavorite(true);
		
		return cardDto;
	}

	@Override
	public boolean deleteFavorite(String userId, long cardId) {
		CardDto cardDto = new CardDto();
		UserDto userDto = new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		cardDto = cardService.getCard(cardId);
		
		List<CardEntity> userFavorites = userEntity.getFavorites();
		for(CardEntity card : userFavorites) {
			if(card.getCardId() == cardId) {
				userFavorites.remove(card);
				break;
			}
		}
		userEntity.setFavorites(userFavorites);
		userRepository.save(userEntity);
		
		return true;
	}
}
