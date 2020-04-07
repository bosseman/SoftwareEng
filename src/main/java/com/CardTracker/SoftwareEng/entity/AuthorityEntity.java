package com.CardTracker.SoftwareEng.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/*
 * Entity for setting authorities to users
 */
@Entity(name = "authorities")
public class AuthorityEntity implements Serializable {

	private static final long serialVersionUID = -1121848235092382869L;

	@Id
	@GeneratedValue
	private long authorityId;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "authorityRoles")
	Collection<RoleEntity> roleAuthority;

	public AuthorityEntity(String name) {
		this.name = name;
	}

	public AuthorityEntity() {

	}

	public long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RoleEntity> getRoleAuthority() {
		return roleAuthority;
	}

	public void setRoleAuthority(Collection<RoleEntity> roleAuthority) {
		this.roleAuthority = roleAuthority;
	}

}
