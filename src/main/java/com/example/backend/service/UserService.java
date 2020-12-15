package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;

import java.util.List;

public interface UserService {

	List<User> findAll();

	User save(User user);

	User update(User user);

	User findById(Integer userId);

	void deleteById(Integer userId);

	List<Role> findAllRolesById(Integer userId);

	User findByUsername(String username);
}