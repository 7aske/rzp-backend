package com.example.backend.service;

import com.example.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

	List<User> findAll();

	boolean delete(User user);

	User save(User user);

	User update(User user);

	User findById(Long idUser);

	User findByUserUsername(String userUsername);

	List<User> findAllByUserEmail(String userEmail);

	List<User> findAllByUserFirstName(String userFirstName);

	List<User> findAllByUserLastName(String userLastName);

	List<User> findAllByUserDisplayName(String userDisplayName);

	boolean deleteById(Long idUser);
}
