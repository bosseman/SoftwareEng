package com.CardTracker.SoftwareEng.ui.response;

public class GetCardDetailsResponseModel {
	private long cardId;
	private String cardName;
	private String cardType;
	private String lastSoldPrice; // We can implement a list of prices with dates in a later time
	private String currentStats; // We can map sell price to stats
	private String playerTeam;
	private String playerSport; // In case we want to expand our domain
	private boolean favorite = false; //Default
	
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

	public String getLastSoldPrice() {
		return lastSoldPrice;
	}

	public void setLastSoldPrice(String lastSoldPrice) {
		this.lastSoldPrice = lastSoldPrice;
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

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

}
