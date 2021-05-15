package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Sort;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.security.annotaions.AllowAdmin;
import rs.digitize.backend.security.annotaions.AllowAuthor;
import rs.digitize.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@AllowAdmin
	@GetMapping
	@ApiOperation(value = "", nickname = "getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "q", required = false) Specification<User> specification,
	                                              @RequestParam(name = "page", required = false) Pageable pageable,
	                                              @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(userService.findAll(specification, sort, pageable));
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "", nickname = "getUserById")
	public ResponseEntity<User> getById(@PathVariable String userId) {
		try {
			return ResponseEntity.ok(userService.findById(Integer.parseInt(userId)));
		} catch (NumberFormatException e) {
			return ResponseEntity.ok(userService.findByUsername(userId));
		}
	}

	@AllowAdmin
	@PostMapping
	@ApiOperation(value = "", nickname = "saveUser")
	public ResponseEntity<User> save(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateUser")
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.ok(userService.update(user));
	}

	@AllowAdmin
	@DeleteMapping("/{userId}")
	@ApiOperation(value = "", nickname = "deleteUserById")
	public void deleteById(@PathVariable Integer userId) {
		userService.deleteById(userId);
	}

	@AllowAuthor
	@GetMapping("/{userId}/roles")
	@ApiOperation(value = "", nickname = "getAllUserRoles")
	public ResponseEntity<List<Role>> getAllRoles(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.findAllRolesById(userId));
	}

}

