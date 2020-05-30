package com.mylearning.springboot.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {

	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	public static void main(String[] args) {
		String password = "Password123"; //admin123 - $2a$10$EfrmD14NcjpE3XZ7dBfuTO4EvqvU/V/ngozzyc8g8n8B.TInXDVOO
		                              //Password123 - $2a$10$.UOCAhLyuBlVl.i13KgTj.ZHfzpfzjMQ1rgBAayLWuBPV6kE2.w9G

		String encryptedPassword = encryptPassword(password);
		
		System.out.println("Encrypted Password: " + encryptedPassword);
	}
}
