package rs.digitize.backend.controller;

import rs.digitize.backend.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	@PostMapping("/verify")
	public ResponseEntity<?> validate() {
		return ResponseEntity.ok().build();
	}
}
