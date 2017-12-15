package com.revature.util;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

	private Logger log = Logger.getRootLogger();

	public String Encrypt(String string) {
		log.trace("encrypting password: " + string);
		java.security.MessageDigest md;
		try {
			md = java.security.MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			log.error("Unable to encrypt passwords: " + e);
		}
		return null;
	}
}