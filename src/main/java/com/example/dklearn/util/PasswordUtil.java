package com.example.dklearn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {
    public static boolean checkPassword(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSymbol = special.matcher(password);

        if(password == null) return false;
        if(password.length() < 8) return false;

        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
        }
        if(numberFlag && capitalFlag && lowerCaseFlag && hasSymbol.find())
            return true;

        return false;
    }
}
