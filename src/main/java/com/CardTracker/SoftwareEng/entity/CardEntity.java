package com.CardTracker.SoftwareEng.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/*
 * Attributes for cards we wish to save
 */
@Entity(name = "cards")
public class CardEntity implements Serializable {

	private static final long serialVersionUID = -9090541494479878065L;
	@Id
	@GeneratedValue
	private long cardId;
	private String cardName;
	private String cardType;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CardPriceHistory> prices; // We can implement a list of prices with dates in a later time

	private String currentStats; // We can map sell price to stats
	private String playerTeam;
	private String playerSport; // In case we want to expand our domain

	@ManyToMany(mappedBy = "favorites")
	private List<UserEntity> likedBy;

	// private boolean favorite = false; //Default
	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public List<CardPriceHistory> getPrices() {
		return prices;
	}

	// Add one price at a time.
	public void setPrices(CardPriceHistory prices) {
		this.prices.add(prices);
	}

	public String getCurrentStats() {
		return currentStats;
	}

	public void setCurrentStats(String currentStats) {
		this.currentStats = currentStats;
	}

	public String getPlayerTeam() {
		return playerTeam;
	}

	public void setPlayerTeam(String playerTeam) {
		this.playerTeam = playerTeam;
	}

	public String getPlayerSport() {
		return playerSport;
	}

	public void setPlayerSport(String playerSport) {
		this.playerSport = playerSport;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<UserEntity> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<UserEntity> likedBy) {
		this.likedBy = likedBy;
	}

}
