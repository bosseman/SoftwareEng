package com.CardTracker.SoftwareEng.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "cards_tb")
@Table(name = "newCardInfo")
public class CardEntity {
	@Id
	@GeneratedValue
	@Column(name = "cardID")
	private long cardID;
	
	
	//@ManyToOne()
	@Column(name = "MatchingId")
	private long player;
	
	@Column(name = "name")
	private String name;
	@Column(name = "Price")
	private String Price;
	@Column(name = "TimeSold")
	private String TimeSold;
	
	@ManyToMany(mappedBy = "favorites")
	Collection<UserEntity> usersLikedMe;
	
	public long getCardID() {
		return cardID;
	}
	public void setCardID(long cardID) {
		this.cardID = cardID;
	}
	public long getPlayerID() {
		return player;
	}
	public void setPlayerID(long playerID) {
		this.player = playerID;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setUsersLikedMe(UserEntity usersLikedMe) {
		this.usersLikedMe.add(usersLikedMe);
	}
	
	
}
