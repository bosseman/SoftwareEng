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
 * Roles with predefined authorities
 */
@Entity(name = "role")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 5666608751230157945L;

	@Id
	@GeneratedValue
	private long rolesId;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles")
	Collection<UserEntity> userRoles;

	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "roleAuthorities", joinColumns = @JoinColumn(name = "rolesId"), inverseJoinColumns = @JoinColumn(name = "authorityId"))
	List<AuthorityEntity> authorityRoles;

	public RoleEntity(String name) {
		this.name = name;
	}

	public RoleEntity() {
		// Hi
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRolesId() {
		return rolesId;
	}

	public void setRolesId(long rolesId) {
		this.rolesId = rolesId;
	}

	public Collection<UserEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Collection<UserEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public List<AuthorityEntity> getAuthorityRoles() {
		return authorityRoles;
	}

	public void setAuthorityRoles(List<AuthorityEntity> authorityRoles) {
		this.authorityRoles = authorityRoles;
	}

}
