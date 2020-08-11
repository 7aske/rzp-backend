package com.example.backend.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.backend.adapter.UserAdapter;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.LoginDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.security.JWTUtils;
import com.example.backend.service.AuthService;
import com.example.backend.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
		try {
			String token = authService.login(loginDTO);

			return ResponseEntity.ok(token);
		} catch (AuthServiceImpl.LoginException e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new ClientError(e.getMessage()));
		}
	}

	@PostMapping("/verify")
	public ResponseEntity<Object> verify(HttpServletRequest request){
		try {
			JWTUtils.getToken(request);
			return ResponseEntity.ok("ok");
		} catch (TokenExpiredException e) {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new ClientError("auth.token.expired"));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new ClientError("auth.unauthorized"));
		}

	}
}
