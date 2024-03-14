package org.blogapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryption {
    public static String hashPassword(String password, String salt) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password + salt);
    }

    public static boolean authenticate(String providedPassword, String storedHash, String salt) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(providedPassword + salt, storedHash);
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}