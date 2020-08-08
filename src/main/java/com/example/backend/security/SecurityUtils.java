package com.example.backend.security;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class SecurityUtils {

	@SuppressWarnings("UnstableApiUsage")
	public static String getSha256(String str) {
		return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
	}

	@SuppressWarnings("UnstableApiUsage")
	public static String getSha512(String str) {
		return Hashing.sha512().hashString(str, StandardCharsets.UTF_8).toString();
	}

	public static boolean hasRole(String[] roles, String roleName) {
		for (String role : roles) {
			if (role.toLowerCase().equals(roleName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}


