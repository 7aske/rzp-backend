package com.example.backend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
	public static final Pattern EMAIL_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static final Pattern PASSWORD_REGEX = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})");

	public static boolean validateEmail(String emailStr) {
		Matcher matcher = EMAIL_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static boolean validatePassword(String passwordStr){
		Matcher matcher = PASSWORD_REGEX.matcher(passwordStr);
		return matcher.matches();
	}
}
