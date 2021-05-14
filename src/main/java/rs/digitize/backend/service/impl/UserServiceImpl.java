package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.repository.UserRepository;
import rs.digitize.backend.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAll(Specification<User> specification, Sort sort, Pageable pageable) {
		if (pageable != null)
			return userRepository.findAll(specification, pageable).toList();
		return userRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public User findById(Integer userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("UserService.notFound"));
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
	public void deleteById(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<Role> findAllRolesById(Integer userId) {
		return findById(userId).getRoles();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("UserService.notFound"));
	}


}