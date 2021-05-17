package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.digitize.backend.data.ChangePasswordDto;
import rs.digitize.backend.data.RegisterUserDto;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.exception.EmailValidationException;
import rs.digitize.backend.exception.InvalidPasswordException;
import rs.digitize.backend.exception.PasswordMismatchException;
import rs.digitize.backend.exception.PasswordValidationException;
import rs.digitize.backend.repository.UserRepository;
import rs.digitize.backend.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static rs.digitize.backend.entity.Role.USER_ROLE;
import static rs.digitize.backend.entity.domain.RecordStatus.ACTIVE;
import static rs.digitize.backend.entity.domain.RecordStatus.EXPIRED;
import static rs.digitize.backend.util.StringUtils.falsy;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static final Pattern EMAIL_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	// 8-40 chars long, at least one number, one lower, one upper
	private static final Pattern PASSWORD_REGEX =
			Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+).{8,40}$");
	//(?=.*[@#$%^&+=]) for special characters

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAll(Specification<User> specification, Sort sort, Pageable pageable) {
		if (pageable != null)
			return userRepository.findAll(specification, pageable).toList();
		return userRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public User findById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User.notFound"));
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User.notFound"));
	}

	@Override
	public User save(User user) {
		if (!falsy(user.getPassword()))
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		else {
			user.setRecordStatus(EXPIRED);
			user.setPassword(passwordEncoder.encode(user.getDefaultPassword()));
		}

		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		User existingUser = findById(user.getId());
		// preserve password
		user.setPassword(existingUser.getPassword());

		return userRepository.save(user);
	}

	@Override
	public void deleteById(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public void changePassword(User user, ChangePasswordDto changePasswordDto) {
		if (user.getRecordStatus() == EXPIRED) {
			if (!changePasswordDto.isValid())
				throw new PasswordMismatchException();
			user.setRecordStatus(ACTIVE);
			if (!user.getRoles().contains(USER_ROLE))
				user.getRoles().add(USER_ROLE);
		} else {
			if (!changePasswordDto.isValid())
				throw new PasswordMismatchException();
			if (!passwordEncoder.matches(changePasswordDto.getPrevious(), user.getPassword()))
				throw new InvalidPasswordException();
		}

		validatePassword(changePasswordDto.getPassword());

		user.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));

		userRepository.save(user);
	}

	@Override
	public User register(RegisterUserDto dto) {
		User user = makeUser(dto);
		user.getRoles().add(USER_ROLE);
		return userRepository.save(user);
	}

	@Override
	public User resetPassword(Integer userId) {
		User user = findById(userId);
		user.setPassword(passwordEncoder.encode(user.getDefaultPassword()));
		user.setRecordStatus(EXPIRED);
		return userRepository.save(user);
	}

	public void validateEmail(String emailStr) {
		Matcher matcher = EMAIL_REGEX.matcher(emailStr);
		if (!matcher.find())
			throw new EmailValidationException();
	}

	public static void validatePassword(String passwordStr) {
		if (passwordStr == null)
			throw new PasswordValidationException();
		Matcher matcher = PASSWORD_REGEX.matcher(passwordStr);
		if (!matcher.matches())
			throw new PasswordValidationException();
	}

	@Override
	public List<Role> findAllRolesById(Integer userId) {
		return findById(userId).getRoles();
	}

	private User makeUser(RegisterUserDto dto) {
		if (!dto.getPassword().equals(dto.getConfirm()))
			throw new PasswordMismatchException();
		validateEmail(dto.getEmail());
		validatePassword(dto.getPassword());

		User user = new User();
		user.setUsername(dto.getUsername().toLowerCase(Locale.ROOT).trim());
		user.setDisplayName(user.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setFirstName(dto.getFirstName().trim());
		user.setLastName(dto.getLastName().trim());
		user.setEmail(dto.getEmail().trim());
		user.setRoles(new ArrayList<>());
		return user;
	}
}