package org.blogapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {
    public static boolean validateEmail(String email){
        if(email.length() < 5 || email.length() > 39) return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean validatePassword(String password){
        if(password.length() > 40) return false;
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
}