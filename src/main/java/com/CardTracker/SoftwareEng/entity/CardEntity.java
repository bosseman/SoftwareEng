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
	private String name;
	private String team;
	private String price;
	private String timeSold; 
	private String position;
	private String age;
	private String height;
	private String draftPosition;
	private String shootingPercentage;
	private String freeThrowPercentage;
	private String usageRate;
	private String threePointPercentage;
	private String freeThrowFrequency;
	private String assistRate;
	private String turnoverRate;
	private String reboundRate;
	private String blockingRate;
	private String stealRate;
	private String defenseRate;

	@ManyToMany(mappedBy = "favorites")
	private List<UserEntity> likedBy;
	
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTimeSold() {
		return timeSold;
	}
	public void setTimeSold(String timeSold) {
		this.timeSold = timeSold;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getDraftPosition() {
		return draftPosition;
	}
	public void setDraftPosition(String draftPosition) {
		this.draftPosition = draftPosition;
	}
	public String getShootingPercentage() {
		return shootingPercentage;
	}
	public void setShootingPercentage(String shootingPercentage) {
		this.shootingPercentage = shootingPercentage;
	}
	public String getFreeThrowPercentage() {
		return freeThrowPercentage;
	}
	public void setFreeThrowPercentage(String freeThrowPercentage) {
		this.freeThrowPercentage = freeThrowPercentage;
	}
	public String getUsageRate() {
		return usageRate;
	}
	public void setUsageRate(String usageRate) {
		this.usageRate = usageRate;
	}
	public String getThreePointPercentage() {
		return threePointPercentage;
	}
	public void setThreePointPercentage(String threePointPercentage) {
		this.threePointPercentage = threePointPercentage;
	}
	public String getFreeThrowFrequency() {
		return freeThrowFrequency;
	}
	public void setFreeThrowFrequency(String freeThrowFrequency) {
		this.freeThrowFrequency = freeThrowFrequency;
	}
	public String getAssistRate() {
		return assistRate;
	}
	public void setAssistRate(String assistRate) {
		this.assistRate = assistRate;
	}
	public String getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(String turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
	public String getReboundRate() {
		return reboundRate;
	}
	public void setReboundRate(String reboundRate) {
		this.reboundRate = reboundRate;
	}
	public String getBlockingRate() {
		return blockingRate;
	}
	public void setBlockingRate(String blockingRate) {
		this.blockingRate = blockingRate;
	}
	public String getStealRate() {
		return stealRate;
	}
	public void setStealRate(String stealRate) {
		this.stealRate = stealRate;
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
	public String getDefenseRate() {
		return defenseRate;
	}
	public void setDefenseRate(String defenseRate) {
		this.defenseRate = defenseRate;
	}
	
}
	