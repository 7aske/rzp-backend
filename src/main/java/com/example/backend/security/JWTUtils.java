package com.example.backend.security;

import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public class JWTUtils {
	public static DecodedJWT getToken(HttpServletRequest request) throws Exception {
		String authHeader = request.getHeader(SecurityConstants.HEADER_NAME);
		if (authHeader == null) {
			throw new MissingHeaderException("request.header.authorization.missing");
		}
		String token = authHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

		return JWTFacade.verifyToken(token);
	}

	public static class MissingHeaderException extends Exception {
		public MissingHeaderException(String message) {
			super(message);
		}
	}
}
