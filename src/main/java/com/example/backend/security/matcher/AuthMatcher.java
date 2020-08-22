package com.example.backend.security.matcher;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.security.JWTUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AuthMatcher {
	private final List<AuthRule> rules;

	AuthMatcher(List<AuthRule> rules) {
		this.rules = rules;
	}

	public boolean authorized(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		HttpMethod method = HttpMethod.resolve(request.getMethod());

		DecodedJWT decoded = null;
		try {
			decoded = JWTUtils.getToken(request);
		} catch (Exception e) {
			System.err.println(String.format("%s %s %s", path, method, e.getMessage()));
		}

		boolean isAuthenticated = decoded != null;

		List<String> roleList = decoded != null ? decoded.getClaim("roles").asList(String.class) : new ArrayList<>();

		for (AuthRule rule : rules) {
			if (rule.match(path, roleList, method, isAuthenticated)) {
				return true;
			}
		}

		return false;
	}

	private boolean hasPattern(String pattern) {
		for (AuthRule rule : rules) {
			if (rule.match(pattern)) {
				return true;
			}
		}
		return false;
	}
}
