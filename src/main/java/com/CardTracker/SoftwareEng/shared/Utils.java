package com.CardTracker.SoftwareEng.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

		private final Random RANDOM = new SecureRandom();
		private final String ALPHABET = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq";
		
		//Generates a public user id of length length
		public String generateUserId(int length) {
			return generateRandomString(length);
		}
		
		//Aux function for generating random public id
		private String generateRandomString(int length) {
			StringBuilder returnValue = new StringBuilder(length);
			
			for(int i = 0; i < length; i++) {
				returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
			}
			
			return new String(returnValue);
		}
	}

