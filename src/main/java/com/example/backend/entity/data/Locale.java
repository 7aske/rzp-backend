package com.example.backend.entity.data;


import java.util.HashSet;
import java.util.Set;

public class Locale  {
	public static final String EN = "en";
	public static final String RS = "rs";
	public static final String DEFAULT_LOCALE = Locale.EN;
	public static final Set<String> VALID_LOCALES = new HashSet<>();

	static {
		VALID_LOCALES.add(EN);
		VALID_LOCALES.add(RS);
	}

	public static boolean isValidLocale(String locale){
		return VALID_LOCALES.contains(locale);
	}

	private Locale(){}
}
