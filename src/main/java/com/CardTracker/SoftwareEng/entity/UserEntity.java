package com.CardTracker.SoftwareEng.entity;

import java.io.Serializable;
import java.util.Collection;
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
/*
 * user data that will be saved
 */
@Entity(name = "users") // Name of table
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -4678780494066680231L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50, unique = true)
	private String userName;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword; // password encrypted --> save this in DB

	private String emailVerificationToken;

	@Column(nullable = false)
	private Boolean emailVerificationStatus = false; // Default

	@ManyToMany
	@JoinTable(name = "usersFavorites", 
		joinColumns = @JoinColumn(name = "userId"), 
		inverseJoinColumns = @JoinColumn(name = "cardId"))
	private List<CardEntity> favorites;

	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "userRoles",
			joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rolesId"))
	private Collection<RoleEntity> roles;

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


	public List<CardEntity> getFavorites() {
		return favorites;
	}

	public void setFavorites(CardEntity favorites) {
		this.favorites.add(favorites);
	}

	public void setFavorites(List<CardEntity> favorites) {
		this.favorites = favorites;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}

}
