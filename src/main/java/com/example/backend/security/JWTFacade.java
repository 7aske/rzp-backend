package com.example.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

public class JWTFacade {
	private static final String ROLE_CLAIM = "roles";
	private static final String USER_CLAIM = "idUser";
	private static final String ISSUER = "rzp";
	private static final String CLAIM_NAME = "app";
	private static final String CLAIM_VALUE = "blog";
	private static final String JWT_SECRET = "secret";
	private static final long VALIDITY = 2 * 60 * 60 * 1000;
	private static final Algorithm ALGORITHM = Algorithm.HMAC256(JWT_SECRET);

	public static String issueToken(String subject, Long idUser, List<String> roleList) {
		try {
			return JWT.create()
					.withIssuer(ISSUER)
					.withIssuedAt(new Date())
					.withClaim(ROLE_CLAIM, roleList)
					.withClaim(USER_CLAIM, idUser)
					.withSubject(subject)
					.withClaim(CLAIM_NAME, CLAIM_VALUE)
					.withExpiresAt(new Date(System.currentTimeMillis() + VALIDITY))
					.sign(ALGORITHM);

		} catch (JWTCreationException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static DecodedJWT verifyToken(String token) throws Exception {
		return JWT.require(ALGORITHM)
				.withIssuer(ISSUER)
				.withClaim(CLAIM_NAME, CLAIM_VALUE)
				.build()
				.verify(token);
	}
}
