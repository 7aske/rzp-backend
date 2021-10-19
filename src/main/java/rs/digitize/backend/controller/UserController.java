package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rs.digitize.backend.data.ChangePasswordDto;
import rs.digitize.backend.data.RegisterUserDto;
import rs.digitize.backend.entity.Notification;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.exception.http.HttpUnauthorizedException;
import rs.digitize.backend.security.annotaions.AllowAdmin;
import rs.digitize.backend.security.annotaions.AllowAuthenticated;
import rs.digitize.backend.security.annotaions.AllowAuthor;
import rs.digitize.backend.security.annotaions.AllowUser;
import rs.digitize.backend.service.NotificationService;
import rs.digitize.backend.service.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final NotificationService notificationService;

	@AllowAdmin
	@GetMapping
	@ApiOperation(value = "", nickname = "getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "q", required = false) Specification<User> specification,
	                                              @RequestParam(name = "page", required = false) Pageable pageable,
	                                              @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(userService.findAll(specification, sort, pageable));
	}

	@GetMapping("/{identifier}")
	@ApiOperation(value = "", nickname = "getUserById")
	public ResponseEntity<User> getById(@PathVariable String identifier) {
		try {
			return ResponseEntity.ok(userService.findById(Integer.parseInt(identifier)));
		} catch (NumberFormatException e) {
			return ResponseEntity.ok(userService.findByUsername(identifier));
		}
	}

	@GetMapping("/notifications")
	@ApiOperation(value = "", nickname = "getNotificationsForUser")
	public ResponseEntity<List<Notification>> getNotificationsForUser(@AuthenticationPrincipal User user,
	                                                                  @RequestParam(name = "page", required = false) Pageable pageable) {
		return ResponseEntity.ok(notificationService.findAllForUser(user, pageable));
	}

	@AllowAdmin
	@PostMapping
	@ApiOperation(value = "", nickname = "saveUser")
	public ResponseEntity<User> save(@RequestBody User user) {
		return ResponseEntity.status(CREATED).body(userService.save(user));
	}

	@PostMapping("/register")
	@ApiOperation(value = "", nickname = "registerUser")
	public ResponseEntity<User> save(@RequestBody RegisterUserDto dto) {
		return ResponseEntity.status(CREATED).body(userService.register(dto));
	}

	@AllowUser
	@PutMapping
	@ApiOperation(value = "", nickname = "updateUser")
	public ResponseEntity<User> update(@AuthenticationPrincipal User auth, @RequestBody User user) {
		if (!auth.isAdmin()) {
			if (!auth.equals(user))
				throw new HttpUnauthorizedException();
			user.setRoles(auth.getRoles());
		}
		return ResponseEntity.ok(userService.update(user));
	}

	@AllowAuthenticated
	@PutMapping("/password")
	@ApiOperation(value = "", nickname = "updateUserPassword")
	public void updateUserPassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordDto passwordDto) {
		userService.changePassword(user, passwordDto);
	}

	@AllowAdmin
	@DeleteMapping("/{userId}/password")
	@ApiOperation(value = "", nickname = "resetUserPassword")
	public ResponseEntity<User> resetUserPassword(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.resetPassword(userId));
	}

	@AllowAdmin
	@PutMapping("/{userId}/enable")
	@ApiOperation(value = "", nickname = "enableUser")
	@ResponseStatus(code = OK)
	public void enableUser(@PathVariable Integer userId) {
		userService.enableUser(userId);
	}

	@AllowAdmin
	@DeleteMapping("/{userId}/enable")
	@ApiOperation(value = "", nickname = "disableUser")
	@ResponseStatus(code = OK)
	public void disableUser(@PathVariable Integer userId) {
		userService.disableUser(userId);
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

