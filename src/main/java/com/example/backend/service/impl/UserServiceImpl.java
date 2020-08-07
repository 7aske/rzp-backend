package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long idUser) {
		if (userRepository.findById(idUser).isPresent()) {
			return userRepository.findById(idUser).get();
		} else {
			return null;
		}
	}

	@Override
	public User findByUserUsername(String userUsername) {
		return userRepository.findByUserUsername(userUsername).orElse(null);
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
	public boolean delete(User user) {
		userRepository.delete(user);
		return !userRepository.findById(user.getIdUser()).isPresent();
	}

	@Override
	public boolean deleteById(Long idUser) {
		userRepository.deleteById(idUser);
		return !userRepository.findById(idUser).isPresent();
	}

}
