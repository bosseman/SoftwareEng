package com.CardTracker.SoftwareEng.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.CardTracker.SoftwareEng.io.repository.UserRepository;
import com.CardTracker.SoftwareEng.service.UserService;

/*
 * Secures API endpoints
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userDetailsService;
	private final BCryptPasswordEncoder encoder;
	private final UserRepository userRepo;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder encoder, UserRepository userRepo) {
		this.encoder = encoder;
		this.userDetailsService = userDetailsService;
		this.userRepo = userRepo;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
				.permitAll().antMatchers(HttpMethod.GET, "/api/cards").permitAll()
				.antMatchers(HttpMethod.GET, "/api/scrap/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/cards/**").permitAll().anyRequest().authenticated().and()
				.addFilter(new AuthenticationFilter(authenticationManager()))
				.addFilter(new AutherizationFilter(authenticationManager(), userRepo)).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Users must be authorized before user REST
																			// services with URI of "/users"
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder); // Just protecting the password
	}
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
        
        return source;
    }

	/*
	 * If we want to set up a new authentication path
	 * 
	 * public AuthenticationFilter getAuthenticationFilter() throws Exception{ final
	 * AuthenticationFilter filter = new
	 * AuthenticationFilter(authenticationManager());
	 * filter.setFilterProcessesUrl("/users/login"); return filter; }
	 * 
	 */
}
