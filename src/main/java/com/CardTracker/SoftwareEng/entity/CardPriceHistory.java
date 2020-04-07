package com.CardTracker.SoftwareEng.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "CardPriceHistory")
public class CardPriceHistory {

	@Id
	@GeneratedValue
	private long cardPriceId;

	private int price;
	private Date soldDate;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public long getCardPriceId() {
		return cardPriceId;
	}

	public void setCardPriceId(long cardPriceId) {
		this.cardPriceId = cardPriceId;
	}

}
