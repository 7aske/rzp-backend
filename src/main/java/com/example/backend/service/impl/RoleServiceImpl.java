package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Role;
import com.example.backend.repository.RoleRepository;
import com.example.backend.service.RoleService;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long idRole) {
		if (roleRepository.findById(idRole).isPresent()) {
			return roleRepository.findById(idRole).get();
		} else {
			return null;
		}
	}

	@Override
	public Role findAllByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName).orElse(null);
	}

	@Override
	public List<Role> findAllByUserUsername(String userUsername) {
		return roleRepository.findAllByUserUsername(userUsername);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Role role) throws Exception {
		roleRepository.delete(role);
	}

	@Override
	public void deleteById(Long idRole) throws Exception {
		roleRepository.deleteById(idRole);
	}

	@Override
	public void deleteByRoleName(String roleName) throws Exception {
		roleRepository.deleteByRoleName(roleName);
	}

}
