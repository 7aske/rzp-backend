package com.example.backend.service.impl;

import com.example.backend.adapter.UserAdapter;
import com.example.backend.entity.Role;
import com.example.backend.entity.data.UserProperty;
import com.example.backend.entity.dto.UserDTO;
import com.example.backend.repository.RoleRepository;
import com.example.backend.security.SecurityUtils;
import com.example.backend.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserDTO findById(Long idUser) {
		return UserAdapter.adapt(userRepository.findById(idUser).orElse(null));
	}

	@Override
	public UserDTO findByUserUsername(String userUsername) {
		return UserAdapter.adapt(userRepository.findByUserUsername(userUsername).orElse(null));
	}

	@Override
	public List<User> findAllByUserEmail(String userEmail) {
		return null;
	}

	@Override
	public List<User> findAllByUserFirstName(String userFirstName) {
		return null;
	}

	@Override
	public List<User> findAllByUserLastName(String userLastName) {
		return null;
	}

	@Override
	public List<User> findAllByUserDisplayName(String userDisplayName) {
		return null;
	}

	@Override
	public UserDTO save(User user) throws UserValidationException {
		validateUser(UserAdapter.adapt(user));
		validateUsername(user.getUserUsername());
		user.setUserUsername(user.getUserUsername().toLowerCase());

		Role userRole = roleRepository.findByRoleName("user").orElse(null);
		if (user.getUserRoles().size() == 0 || !user.getUserRoles().contains(userRole)){
			List<Role> roles = user.getUserRoles();
			roles.add(userRole);
			user.setUserRoles(roles);
		}

		user.setUserPassword(SecurityUtils.getSha512(user.getUserPassword()));
		return UserAdapter.adapt(userRepository.save(user));
	}

	@Override
	public UserDTO update(UserDTO userDTO) throws UserValidationException {
		User user = userRepository.findById(userDTO.getIdUser()).orElseThrow(() -> new UserValidationException("user.update.user-not-found"));
		validateUser(userDTO);
		user.setUserUsername(userDTO.getUserUsername().toLowerCase());
		user.setUserDisplayName(userDTO.getUserDisplayName());
		user.setUserFirstName(userDTO.getUserFirstName());
		user.setUserLastName(userDTO.getUserLastName());
		user.setUserAbout(userDTO.getUserAbout());
		user.setUserEmail(userDTO.getUserEmail());
		user.setUserAddress(userDTO.getUserAbout());
		user.setUserRoles(userDTO.getUserRoles()
				.stream()
				.map(r -> roleRepository.findByRoleName(r).orElse(null))
				.collect(Collectors.toList()));
		return UserAdapter.adapt(userRepository.save(user));
	}

	@Override
	public void delete(User user) throws Exception {
		userRepository.delete(user);
	}

	@Override
	public void deleteById(Long idUser) throws Exception {
		userRepository.deleteById(idUser);
	}


	@Override
	public UserDTO updateProperty(Long idUser, String property, Object value) throws UserValidationException {
		User user = userRepository.findById(idUser).orElseThrow(() -> new UserValidationException("user.update.user-not-found"));

		UserProperty prop = UserProperty.valueOf(property);
		switch (prop) {
			case userUsername:
				validateUsername((String) value);
				user.setUserUsername((String) value);
				break;
			case userDisplayName:
				validateDisplayName((String) value);
				user.setUserDisplayName((String) value);
				break;
			case userFirstName:
				validateFirstName((String) value);
				user.setUserFirstName((String) value);
				break;
			case userLastName:
				validateLastName((String) value);
				user.setUserLastName((String) value);
				break;
			case userPassword:
				validatePassword((String) value);
				user.setUserPassword(SecurityUtils.getSha512((String) value));
				break;
			case userAbout:
				validateAbout((String) value);
				user.setUserAbout((String) value);
				break;
			case userEmail:
				validateEmail((String) value);
				user.setUserEmail((String) value);
				break;
			case userAddress:
				validateAddress((String) value);
				user.setUserAddress((String) value);
				break;
		}
		return UserAdapter.adapt(userRepository.save(user));
	}

	@Override
	public UserDTO updatePassword(Long idUser, String password, String confirmPassword, String newPassword) throws UserValidationException {
		if (!password.equals(confirmPassword)) {
			throw new UserValidationException("user.update.password-not-matching");
		}
		User user = userRepository.findById(idUser).orElseThrow(() -> new UserValidationException("user.update.user-not-found"));

		if (!user.getUserPassword().equals(SecurityUtils.getSha512(password))) {
			throw new UserValidationException("auth.login.invalid-credentials");
		}
		validatePassword(newPassword);

		user.setUserPassword(SecurityUtils.getSha512(newPassword));
		return UserAdapter.adapt(userRepository.save(user));
	}

	private void validateUser(UserDTO userDTO) throws UserValidationException {
		validateDisplayName(userDTO.getUserDisplayName());
		validateFirstName(userDTO.getUserFirstName());
		validateLastName(userDTO.getUserLastName());
		validateAbout(userDTO.getUserAbout());
		validateEmail(userDTO.getUserEmail());
		validateAddress(userDTO.getUserAddress());
		validateRoles(userDTO.getUserRoles());
	}

	void validateRoles(List<String> roles) throws UserValidationException {
		for (String userRole : roles) {
			roleRepository.findByRoleName(userRole).orElseThrow(() -> new UserValidationException("user.update.role-not-found"));
		}
	}

	void validateUsername(String value) throws UserValidationException {
		if (value == null) {
			throw new UserValidationException("user.update.username-invalid");
		}
		if (value.isEmpty()) {
			throw new UserValidationException("user.update.username-empty");
		}

		if (value.length() < 4) {
			throw new UserValidationException("user.update.username-short");
		}

		if (userRepository.findByUserUsername(value).isPresent()) {
			throw new UserValidationException("user.update.user-exists");
		}
	}


	void validateDisplayName(String value) throws UserValidationException {
		if (value == null) {
			throw new UserValidationException("user.update.displayname-invalid");
		}
		if (value.isEmpty()) {
			throw new UserValidationException("user.update.displayname-empty");
		}

		if (value.length() < 4) {
			throw new UserValidationException("user.update.displayname-short");
		}

	}

	void validateFirstName(String value) throws UserValidationException {
		if (value == null) {
			throw new UserValidationException("user.update.firstname-invalid");
		}
		if (value.isEmpty()) {
			throw new UserValidationException("user.update.firstname-empty");
		}

	}

	void validateLastName(String value) throws UserValidationException {
		if (value == null) {
			throw new UserValidationException("user.update.lastname-invalid");
		}
		if (value.isEmpty()) {
			throw new UserValidationException("user.update.lastname-empty");
		}

	}

	void validateEmail(String value) throws UserValidationException {
		if (value == null || !ValidationUtil.validateEmail(value)) {
			throw new UserValidationException("user.update.email-invalid");
		}
		if (value.isEmpty()) {
			throw new UserValidationException("user.update.email-empty");
		}
	}

	void validateAbout(String value) throws UserValidationException {
		if (value == null) {
			throw new UserValidationException("user.update.about-invalid");
		}
	}

	void validateAddress(String value) throws UserValidationException {
		if (value == null) {
			throw new UserValidationException("user.update.address-invalid");
		}
	}

	void validatePassword(String value) throws UserValidationException {
		System.out.println(value);
		if (value == null || !ValidationUtil.validatePassword(value)) {
			throw new UserValidationException("user.update.password-invalid");
		}
		if (value.isEmpty()) {
			throw new UserValidationException("user.update.password-empty");
		}
	}

	public static class UserValidationException extends Exception {
		public UserValidationException(String message) {
			super(message);
		}
	}
}
