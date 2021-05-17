package rs.digitize.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static rs.digitize.backend.security.SecurityConstants.*;

@Component
public class JwtProvider {
	private final UserService userService;
	@Value("${jwt.secret-key:secret}")
	private String secretKey;
	@Value("${jwt.expire-length:7200000}")
	private long validityInMilliseconds;

	public JwtProvider(UserService userService) {
		this.userService = userService;
	}

	public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(validity)
				.claim("user", username)
				.claim(CLAIM_ROLES_KEY, getAuthoritiesAsStringList(authorities))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	private static List<String> getAuthoritiesAsStringList(Collection<? extends GrantedAuthority> authorities){
		return authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
	}

	public Authentication getAuthentication(String token) {
		User user = this.userService.findByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getRoles());
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	private Claims getClaims(String token){
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(HEADER_STRING);

		if (bearerToken == null || !bearerToken.startsWith(TOKEN_PREFIX)) {
			return null;
		}

		return bearerToken.substring(TOKEN_PREFIX.length());
	}

	public boolean validateToken(String token) {
		if (token == null || token.isEmpty())
			return false;
		try {
			Claims claims = getClaims(token);
			return !claims.getExpiration().before(new Date());
		} catch (NullPointerException | JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
