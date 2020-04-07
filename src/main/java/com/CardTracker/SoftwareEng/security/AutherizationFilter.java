package com.CardTracker.SoftwareEng.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.CardTracker.SoftwareEng.entity.UserEntity;
import com.CardTracker.SoftwareEng.io.repository.UserRepository;

import io.jsonwebtoken.Jwts;

/*
 * Check JSON token to ensure user is who they say they are
 */
public class AutherizationFilter extends BasicAuthenticationFilter {

	private final UserRepository userRepository;
	
	public AutherizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(SecurityConstants.HEADER_STRING);

		if (token != null) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

			String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody()
					.getSubject();
			if (user != null) {
				UserEntity userEntity = userRepository.findByUserName(user);
				UserPrincipal userPrin = new UserPrincipal(userEntity);
				return new UsernamePasswordAuthenticationToken(user, null, userPrin.getAuthorities());
			}
			return null;
		}
		return null;
	}
}
