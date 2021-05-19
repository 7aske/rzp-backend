package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.repository.RoleRepository;
import rs.digitize.backend.service.RoleService;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Integer roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new NoSuchElementException("RoleService.notFound"));
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name)
				.orElseThrow(() -> new NoSuchElementException("RoleService.notFound"));
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
	public void deleteById(Integer roleId) {
		roleRepository.deleteById(roleId);
	}

	@Override
	public List<User> findAllUsersById(Integer roleId) {
		return findById(roleId).getUsers();
	}


}