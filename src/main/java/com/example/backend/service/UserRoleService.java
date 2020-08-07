package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import com.example.backend.entity.UserRole;
import java.util.List;

public interface UserRoleService {

	List<UserRole> findAll();

	boolean delete(UserRole userRole);

	UserRole save(UserRole userRole);

	UserRole update(UserRole userRole);

	UserRole findByIdRoleAndIdUser(Long idRole, Long idUser);

	List<UserRole> findAllByIdRole(Long idRole);

	List<UserRole> findAllByIdUser(Long idUser);

	boolean deleteByIdRoleAndIdUser(Long idRole, Long idUser);

	boolean deleteAllByIdRole(Long idRole);

	boolean deleteAllByIdUser(Long idUser);
}
