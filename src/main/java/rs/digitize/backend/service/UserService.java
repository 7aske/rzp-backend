package rs.digitize.backend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.data.ChangePasswordDto;
import rs.digitize.backend.data.RegisterUserDto;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;

import java.util.List;

public interface UserService {

	List<User> findAll();

	List<User> findAll(Specification<User> specification, Sort sort, Pageable pageable);

	User save(User user);

	User update(User user);

	User findById(Integer userId);

	void deleteById(Integer userId);

	List<Role> findAllRolesById(Integer userId);

	User findByUsername(String username);

	void changePassword(User user, ChangePasswordDto passwordDto);

	User register(RegisterUserDto dto);

	User resetPassword(Integer userId);
}