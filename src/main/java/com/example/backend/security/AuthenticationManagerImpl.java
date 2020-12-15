package com.example.backend.security;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.util.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

@Component
@RequiredArgsConstructor
@Primary
public class AuthenticationManagerImpl implements AuthenticationManager {
	private final UserRepository userRepository;
	private final Environment env;
	@Resource(name = "errorMessages")
	private Properties errors;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Optional<User> userOptional = userRepository.findByUsername(username);

		if (!userOptional.isPresent())
			throw new UsernameNotFoundException(errors.getProperty("auth.login.user-not-found"));

		User user = userOptional.get();

		if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			return new UsernamePasswordAuthenticationToken(username, password, user.getRoles());
		}

		if (!HashUtils.getSha512(password).equals(user.getPassword()))
			throw new BadCredentialsException(errors.getProperty("auth.login.invalid-credentials"));

		return new UsernamePasswordAuthenticationToken(username, password, user.getRoles());
	}
}
