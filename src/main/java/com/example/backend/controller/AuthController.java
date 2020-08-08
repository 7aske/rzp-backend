package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.LoginDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.service.AuthService;
import com.example.backend.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
		try {
			User user = authService.login(loginDTO);

			return ResponseEntity.ok(user.getDTO());
		} catch (AuthServiceImpl.LoginException e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new ClientError(e.getMessage()));
		}
	}
}
