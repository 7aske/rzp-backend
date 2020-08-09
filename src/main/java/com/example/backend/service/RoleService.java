package com.example.backend.service;

import com.example.backend.entity.Role;

import java.util.List;

public interface RoleService {

	List<Role> findAll();

	void delete(Role role) throws Exception;

	Role save(Role role);

	Role update(Role role);

	Role findById(Long idRole);

	Role findAllByRoleName(String roleName);

	List<Role> findAllByUserUsername(String userUsername);

	void deleteById(Long idRole) throws Exception;

	void deleteByRoleName(String roleName) throws Exception;
}
