package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.UserDTO;
import com.example.backend.service.impl.UserServiceImpl;
// import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

	List<User> findAll();

	void delete(User user) throws Exception;

	User save(User user);

	User update(User user);

	UserDTO findById(Long idUser);

	UserDTO findByUserUsername(String userUsername);

	List<User> findAllByUserEmail(String userEmail);

	List<User> findAllByUserFirstName(String userFirstName);

	List<User> findAllByUserLastName(String userLastName);

	List<User> findAllByUserDisplayName(String userDisplayName);

	UserDTO updateProperty(Long idUser, String property, Object value) throws UserServiceImpl.UserValidationException;

	// List<GrantedAuthority> getAllRolesByUsername(String username);

	void deleteById(Long idUser) throws Exception;
}
