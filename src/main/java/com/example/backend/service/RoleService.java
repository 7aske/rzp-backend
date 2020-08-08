package com.example.backend.service;

import com.example.backend.entity.Role;
import java.util.List;

public interface RoleService {

	List<Role> findAll();

	boolean delete(Role role);

	Role save(Role role);

	Role update(Role role);

	Role findById(Long idRole);

	Role findAllByRoleName(String roleName);

	List<Role> findAllByUserUsername(String userUsername);

	boolean deleteById(Long idRole);

	boolean deleteByRoleName(String roleName);
}
