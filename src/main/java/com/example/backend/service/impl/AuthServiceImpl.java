package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.LoginDTO;
import com.example.backend.entity.dto.RegisterDTO;
import com.example.backend.security.SecurityUtils;
import com.example.backend.service.AuthService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserService userService;

	@Override
	public User login(LoginDTO loginDTO) throws LoginException {
		User user = userService.findByUserUsername(loginDTO.getUsername());
		if (user == null) {
			throw new LoginException("auth.login.user-not-found");
		}

		if (SecurityUtils.getSha512(loginDTO.getPassword()).equals(user.getUserPassword())) {
			return user;
		}

		throw new LoginException("auth.login.invalid-password");
	}

	public static class LoginException extends Exception {
		public LoginException(String message) {
			super(message);
		}
	}

	@Override
	public User register(RegisterDTO registerDTO) {
		return null;
	}
}
