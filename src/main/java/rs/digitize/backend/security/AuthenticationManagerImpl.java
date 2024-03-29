package rs.digitize.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.repository.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Properties;

import static rs.digitize.backend.entity.domain.RecordStatus.EXPIRED;
import static rs.digitize.backend.util.StringUtils.falsy;

@Component
@RequiredArgsConstructor
@Primary
public class AuthenticationManagerImpl implements AuthenticationManager {
	private final UserRepository userRepository;
	private final Environment env;
	private final PasswordEncoder passwordEncoder;
	@Resource(name = "errorMessages")
	private Properties errors;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		Object password = authentication.getCredentials();

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(errors.getProperty("auth.login.user-not-found")));

		if (user.isDisabled())
			 throw new DisabledException(errors.getProperty("auth.login.user-disabled"));

		if (falsy((String)password))
			throw new BadCredentialsException(errors.getProperty("auth.login.invalid-credentials"));

		if (!passwordEncoder.matches((String) password, user.getPassword()))
			throw new BadCredentialsException(errors.getProperty("auth.login.invalid-credentials"));

		if (user.isCredentialsExpired())
			return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

		return new UsernamePasswordAuthenticationToken(username, password, user.getRoles());
	}
}
