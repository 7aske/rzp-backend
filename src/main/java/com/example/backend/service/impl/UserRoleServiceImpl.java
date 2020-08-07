package com.example.backend.service.impl;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.UserRole;
import com.example.backend.repository.UserRoleRepository;
import com.example.backend.service.UserRoleService;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	@Override
	public UserRole update(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	@Override
	public boolean delete(UserRole userRole) {
		userRoleRepository.delete(userRole);
		return true;
	}


	@Override
	public UserRole findByIdRoleAndIdUser(Long idRole, Long idUser) {
		return userRoleRepository.findByIdRoleAndIdUser(idRole, idUser).orElse(null);
	}

	@Override
	public List<UserRole> findAllByIdRole(Long idRole) {
		return userRoleRepository.findAllByIdRole(idRole);
	}

	@Override
	public List<UserRole> findAllByIdUser(Long idUser) {
		return userRoleRepository.findAllByIdUser(idUser);
	}


	@Override
	public boolean deleteByIdRoleAndIdUser(Long idRole, Long idUser) {
		return userRoleRepository.deleteByIdRoleAndIdUser(idRole, idUser);
	}

	@Override
	public boolean deleteAllByIdRole(Long idRole) {
		return userRoleRepository.deleteAllByIdRole(idRole);
	}

	@Override
	public boolean deleteAllByIdUser(Long idUser) {
		return userRoleRepository.deleteAllByIdUser(idUser);
	}

}
