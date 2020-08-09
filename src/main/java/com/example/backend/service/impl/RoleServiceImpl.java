package com.example.backend.service.impl;

import com.example.backend.entity.Role;
import com.example.backend.entity.Role;
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
		return roleRepository.findById(idRole).orElse(null);
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
	public Role save(Role role) throws RoleValidationException {
		validate(role);
		if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
			throw new RoleServiceImpl.RoleValidationException("role.save.name-exists");
		}
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role newRole) throws RoleValidationException {
		validate(newRole);
		Role role = roleRepository.findByIdRole(newRole.getIdRole()).orElseThrow(() -> new RoleServiceImpl.RoleValidationException("role.save.role-not-found"));
		boolean isRoleNameValid = role.getRoleName().equals(newRole.getRoleName()) ||
				!roleRepository.findByRoleName(newRole.getRoleName()).isPresent();

		if (!isRoleNameValid) {
			throw new RoleServiceImpl.RoleValidationException("role.save.name-exists");
		}

		role.setRoleName(newRole.getRoleName());

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

	private void validate(Role role) throws RoleServiceImpl.RoleValidationException {
		if (role.getRoleName() == null || role.getRoleName().isEmpty()) {
			throw new RoleServiceImpl.RoleValidationException("role.save.name-invalid");
		}
	}

	public static class RoleValidationException extends Exception {
		public RoleValidationException(String message) {
			super(message);
		}
	}
}
