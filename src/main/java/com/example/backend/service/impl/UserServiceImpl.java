package com.example.backend.service.impl;

import com.example.backend.adapter.UserAdapter;
import com.example.backend.adapter.UserDTOAdapter;
import com.example.backend.entity.dto.UserDTO;
import com.example.backend.repository.RoleRepository;
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

}
