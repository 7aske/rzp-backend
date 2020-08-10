package com.example.backend.security;

import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public class JWTUtils {
	public static DecodedJWT getToken(HttpServletRequest request) {
		String authHeader = request.getHeader(SecurityConstants.HEADER_NAME);
		if (authHeader == null) {
			return null;
		}
		String token = authHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

		try {
			return JWTFacade.verifyToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
