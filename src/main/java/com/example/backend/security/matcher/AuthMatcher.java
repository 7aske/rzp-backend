package com.example.backend.security.matcher;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.security.JWTFacade;
import com.example.backend.security.SecurityConstants;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuthMatcher {
	private final List<AuthRule> rules;

	AuthMatcher(List<AuthRule> rules) {
		this.rules = rules;
	}

	public boolean authorized(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (!hasPattern(path)) {
			return true;
		}

		String authHeader = request.getHeader(SecurityConstants.HEADER_NAME);
		if (authHeader == null) {
			return false;
		}
		String token = authHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

		DecodedJWT decoded = JWTFacade.verifyToken(token);
		if (decoded == null) {
			return false;
		}

		List<String> roleList = decoded.getClaim("roles").asList(String.class);
		System.out.println(roleList);
		HttpMethod method = HttpMethod.resolve(request.getMethod());

		for (AuthRule rule : rules) {
			if (rule.match(path, roleList, method)){
				return true;
			}
		}

		return false;
	}

	private boolean hasPattern(String pattern){
		for (AuthRule rule : rules) {
			System.out.println(rule.getPattern());
			if (rule.match(pattern)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "AuthMatcher{" +
				"rules=" + rules +
				'}';
	}
}
