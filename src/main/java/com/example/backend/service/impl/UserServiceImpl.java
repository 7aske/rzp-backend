package com.example.backend.service.impl;

import com.example.backend.adapter.UserAdapter;
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

import java.util.List;

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

	// @Override
	// public List<GrantedAuthority> getAllRolesByUsername(String username) {
	// 	List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
	// 	for (Role role : roleRepository.findAllByUserUsername(username)) {
	// 		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
	// 	}
	// 	return grantedAuthorityList;
	// }

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
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
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
