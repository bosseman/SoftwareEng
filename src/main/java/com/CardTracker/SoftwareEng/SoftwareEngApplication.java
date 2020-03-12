package com.CardTracker.SoftwareEng;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*
Main class. Places dependents for future injection.
*/
@SpringBootApplication
public class SoftwareEngApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SoftwareEngApplication.class, args);
		
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}
