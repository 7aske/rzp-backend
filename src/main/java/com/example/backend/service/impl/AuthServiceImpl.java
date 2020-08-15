package com.example.backend.service.impl;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.LoginDTO;
import com.example.backend.entity.dto.RegisterDTO;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.JWTFacade;
import com.example.backend.security.SecurityConstants;
import com.example.backend.security.SecurityUtils;
import com.example.backend.security.ServletAttributes;
import com.example.backend.service.AuthService;
import com.example.backend.service.UserService;
import com.sun.corba.se.spi.encoding.CorbaOutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public String login(LoginDTO loginDTO) throws LoginException {
		User user = userRepository.findByUserUsername(loginDTO.getUsername().toLowerCase()).orElse(null);
		if (user == null) {
			throw new LoginException("auth.login.invalid-credentials");
		}

		if (SecurityUtils.getSha512(loginDTO.getPassword()).equals(user.getUserPassword())) {
			HttpServletResponse servletResponse = (HttpServletResponse) ServletAttributes.getResponse();
			List<String> roleNames = user.getUserRoles().stream().map(Role::getRoleName).collect(Collectors.toList());

			String token = JWTFacade.issueToken("login", user.getIdUser(), roleNames);
			String headerValue = SecurityConstants.TOKEN_PREFIX + token;
			Cookie authCookie = new Cookie(SecurityConstants.COOKIE_NAME, token);

			servletResponse.addCookie(authCookie);
			servletResponse.setHeader(SecurityConstants.HEADER_NAME, headerValue);
			return headerValue;
		}

		throw new LoginException("auth.login.invalid-credentials");
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
