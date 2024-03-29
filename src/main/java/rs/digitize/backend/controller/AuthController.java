package rs.digitize.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.digitize.backend.data.LoginResponse;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.security.JwtProvider;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class AuthController {
	private final JwtProvider jwtProvider;

	@PostMapping("/verify")
	public ResponseEntity<?> validate() {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/refresh")
	public ResponseEntity<LoginResponse> refresh(@AuthenticationPrincipal User user) {
		String token = jwtProvider.createToken(user.getUsername(), user.getRoles());
		String refreshToken = jwtProvider.createRefreshToken(user.getUsername());
		return ResponseEntity.ok(LoginResponse.builder().token(token).refresh(refreshToken).build());
	}
}
