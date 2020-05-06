package com.CardTracker.SoftwareEng.shared.dto;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.CardTracker.SoftwareEng.entity.UserEntity;
import com.CardTracker.SoftwareEng.entity.playerInfo;

public class CardDetailsDTO {
	private long cardID;

	private playerInfo player;
	
	
	private String Name;

	private String Price;

	private String TimeSold;
	
	Collection<UserEntity> usersLikedMe;

	public long getCardID() {
		return cardID;
	}

	public void setCardID(long cardID) {
		this.cardID = cardID;
	}

	public playerInfo getPlayer() {
		return player;
	}

	public void setPlayer(playerInfo player) {
		this.player = player;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getTimeSold() {
		return TimeSold;
	}

	public void setTimeSold(String timeSold) {
		TimeSold = timeSold;
	}

	public Collection<UserEntity> getUsersLikedMe() {
		return usersLikedMe;
	}

	public void setUsersLikedMe(Collection<UserEntity> usersLikedMe) {
		this.usersLikedMe = usersLikedMe;
	}

	
}
