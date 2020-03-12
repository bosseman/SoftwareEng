package com.CardTracker.SoftwareEng.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.CardTracker.SoftwareEng.entity.CardEntity;
//User data transfer object
public class UserDto implements Serializable {

	private static final long serialVersionUID = -1095453772425672285L;
	private long id;
	private String userId;
	private String userName;
	private String email;
	private String password; // plaintext
	private String encryptedPassword; // password encrypted --> save this in DB
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false; //default
	private List<CardDto> favorites;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<CardDto> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<CardDto> favorites) {
		this.favorites = favorites;
	}

}
