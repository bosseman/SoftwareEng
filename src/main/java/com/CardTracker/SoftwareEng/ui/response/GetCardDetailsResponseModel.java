package com.CardTracker.SoftwareEng.ui.response;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.CardTracker.SoftwareEng.entity.CardEntity;
import com.CardTracker.SoftwareEng.entity.UserEntity;

public class GetCardDetailsResponseModel {

	private long playerId;

	private String matchingId;

	private String name;

	private String Team;

	private List<CardEntity> cardDetails;

	private String Position;

	private String Age;

	private String Height;

	private String Weight;

	private String DraftPosition;

	private String ShootingPer;

	private String FreeThrowPer;

	private String UsageRate;

	private String ThreePointPer;

	private String FreeThrowFreq;

	private String AssistPer;

	private String TurnoverPer;

	private String ReboundPer;

	private String BlockPer;

	private String StealPer;

	private String DefensiveRating;

	private List<UserEntity> likedBy;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getMatchingId() {
		return matchingId;
	}

	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return Team;
	}

	public void setTeam(String team) {
		Team = team;
	}

	public List<CardEntity> getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(List<CardEntity> cardDetails) {
		this.cardDetails = cardDetails;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public String getDraftPosition() {
		return DraftPosition;
	}

	public void setDraftPosition(String draftPosition) {
		DraftPosition = draftPosition;
	}

	public String getShootingPer() {
		return ShootingPer;
	}

	public void setShootingPer(String shootingPer) {
		ShootingPer = shootingPer;
	}

	public String getFreeThrowPer() {
		return FreeThrowPer;
	}

	public void setFreeThrowPer(String freeThrowPer) {
		FreeThrowPer = freeThrowPer;
	}

	public String getUsageRate() {
		return UsageRate;
	}

	public void setUsageRate(String usageRate) {
		UsageRate = usageRate;
	}

	public String getThreePointPer() {
		return ThreePointPer;
	}

	public void setThreePointPer(String threePointPer) {
		ThreePointPer = threePointPer;
	}

	public String getFreeThrowFreq() {
		return FreeThrowFreq;
	}

	public void setFreeThrowFreq(String freeThrowFreq) {
		FreeThrowFreq = freeThrowFreq;
	}

	public String getAssistPer() {
		return AssistPer;
	}

	public void setAssistPer(String assistPer) {
		AssistPer = assistPer;
	}

	public String getTurnoverPer() {
		return TurnoverPer;
	}

	public void setTurnoverPer(String turnoverPer) {
		TurnoverPer = turnoverPer;
	}

	public String getReboundPer() {
		return ReboundPer;
	}

	public void setReboundPer(String reboundPer) {
		ReboundPer = reboundPer;
	}

	public String getBlockPer() {
		return BlockPer;
	}

	public void setBlockPer(String blockPer) {
		BlockPer = blockPer;
	}

	public String getStealPer() {
		return StealPer;
	}

	public void setStealPer(String stealPer) {
		StealPer = stealPer;
	}

	public String getDefensiveRating() {
		return DefensiveRating;
	}

	public void setDefensiveRating(String defensiveRating) {
		DefensiveRating = defensiveRating;
	}

	public List<UserEntity> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<UserEntity> likedBy) {
		this.likedBy = likedBy;
	}
	
	

}
