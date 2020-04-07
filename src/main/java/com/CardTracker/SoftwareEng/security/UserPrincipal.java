package com.CardTracker.SoftwareEng.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.CardTracker.SoftwareEng.entity.AuthorityEntity;
import com.CardTracker.SoftwareEng.entity.RoleEntity;
import com.CardTracker.SoftwareEng.entity.UserEntity;

public class UserPrincipal implements UserDetails {


	private static final long serialVersionUID = 8892194887780666665L;

	UserEntity userEntity;
	
	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<AuthorityEntity> authEntity = new ArrayList<>();
		
		
		Collection<RoleEntity> roles = userEntity.getRoles();
		
		if(roles == null) return authorities;
		
		roles.forEach((role)->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authEntity.addAll(role.getAuthorityRoles());
			
		});
		
		authEntity.forEach((authorityEntity)->{
			authorities.add(new SimpleGrantedAuthority((authorityEntity.getName())));
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userEntity.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
