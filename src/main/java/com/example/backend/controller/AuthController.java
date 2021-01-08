package com.example.backend.controller;

import com.example.backend.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final JwtProvider jwtProvider;

	@PostMapping("/validate")
	public ResponseEntity<?> validate() {
		return ResponseEntity.ok().build();
	}
}
