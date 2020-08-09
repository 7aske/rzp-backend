package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.service.impl.RoleServiceImpl;

import java.util.List;

public interface RoleService {

	List<Role> findAll();

	void delete(Role role) throws Exception;

	Role save(Role role) throws RoleServiceImpl.RoleValidationException;

	Role update(Role role) throws RoleServiceImpl.RoleValidationException;

	Role findById(Long idRole);

	Role findAllByRoleName(String roleName);

	List<Role> findAllByUserUsername(String userUsername);

	void deleteById(Long idRole) throws Exception;

	void deleteByRoleName(String roleName) throws Exception;
}
