package com.dinerinfo.controller;

import java.security.SecureRandom;

public class DinerPasswordGenerator {
	
//	 密碼預設為6碼，由英文大小寫、數字、和@#*+.五個符號所組成的亂碼
	
	 private static final String CHARACTERS = 
		        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#*+.";

		    private static final SecureRandom random = new SecureRandom();

		    public static String generateTemporaryPassword(int length) {
		        StringBuilder result = new StringBuilder(length);
		        for (int i = 0; i < length; i++) {
		            result.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		        }
		        return result.toString();
		    }
}
