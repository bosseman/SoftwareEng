package com.CardTracker.SoftwareEng.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Attributes for cards we wish to save
 */
@Entity(name = "playerInfo")
@Table(name = "newPlayerInfo")
public class playerInfo implements Serializable {

	private static final long serialVersionUID = -9090541494479878065L;
	@Id
	@GeneratedValue
	@Column(name = "playerID")
	private long playerId;
	
	//@Column(name = "matchingId")
	private String matchingId;
	
	public String getMatchingId() {
		return matchingId;
	}

	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}

	public List<CardEntity> getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(List<CardEntity> cardDetails) {
		this.cardDetails = cardDetails;
	}

	@Column(name = "name")
	private String name;
	@Column(name = "Team")
	private String Team;
	
	@OneToMany(mappedBy="player")
	private List<CardEntity> cardDetails;
	
	@Column(name = "Position")
	private String Position;
	@Column(name = "Age")
	private String Age;
	@Column(name = "Height")
	private String Height;
	@Column(name = "Weight")
	private String Weight;
	@Column(name = "DraftPosition")
	private String DraftPosition;
	@Column(name = "ShootingPer")
	private String ShootingPer;
	@Column(name = "FreeThrowPer")
	private String FreeThrowPer;
	@Column(name = "UsageRate")
	private String UsageRate;
	@Column(name = "ThreePointPer")
	private String ThreePointPer;
	@Column(name = "FreeThrowFreq")
	private String FreeThrowFreq;
	@Column(name = "AssistPer")
	private String AssistPer;
	@Column(name = "TurnoverPer")
	private String TurnoverPer;
	@Column(name = "ReboundPer")
	private String ReboundPer;
	@Column(name = "BlockPer")
	private String BlockPer;
	@Column(name = "StealPer")
	private String StealPer;
	@Column(name = "DefensiveRating")
	private String DefensiveRating;

	@ManyToMany(mappedBy = "favorites")
	private List<UserEntity> likedBy;

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return this.name;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}