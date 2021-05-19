package rs.digitize.backend.service;

import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;

import java.util.List;

public interface RoleService {

	List<Role> findAll();

	Role save(Role role);

	Role update(Role role);

	Role findById(Integer roleId);

	Role findByName(String roleId);

	void deleteById(Integer roleId);

	List<User> findAllUsersById(Integer roleId);

}