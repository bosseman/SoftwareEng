package com.CardTracker.SoftwareEng.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.CardTracker.SoftwareEng.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

		private final UserService userDetailsService;
		private final BCryptPasswordEncoder encoder;
		
		public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder encoder) {
			this.encoder = encoder;
			this.userDetailsService = userDetailsService;
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
			.antMatchers(HttpMethod.GET, "/cards").permitAll().
			anyRequest().authenticated().and().addFilter(new AuthenticationFilter(authenticationManager()))
			.addFilter(new AutherizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Users must be authorized before user REST services with URI of "/users"
		}
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(encoder); //Just protecting the password
		}
		
		/*
		 * If we want to set up a new authentication path
		 * 
		public AuthenticationFilter getAuthenticationFilter() throws Exception{
			final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
			filter.setFilterProcessesUrl("/users/login");
			return filter;
		}
		
		*/
}
