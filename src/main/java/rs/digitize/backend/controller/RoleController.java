package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.digitize.backend.entity.Role;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.security.annotaions.AllowAdmin;
import rs.digitize.backend.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
	private final RoleService roleService;

	@AllowAdmin
	@GetMapping
	@ApiOperation(value = "", nickname = "getAllRoles")
	public ResponseEntity<List<Role>> getAll() {
		return ResponseEntity.ok(roleService.findAll());
	}

	@AllowAdmin
	@GetMapping("/{roleId}")
	@ApiOperation(value = "", nickname = "getRoleById")
	public ResponseEntity<Role> getById(@PathVariable Integer roleId) {
		return ResponseEntity.ok(roleService.findById(roleId));
	}

	@AllowAdmin
	@PostMapping
	@ApiOperation(value = "", nickname = "saveRole")
	public ResponseEntity<Role> save(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.save(role));
	}

	@AllowAdmin
	@PutMapping
	@ApiOperation(value = "", nickname = "updateRole")
	public ResponseEntity<Role> update(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.update(role));
	}

	@AllowAdmin
	@DeleteMapping("/{roleId}")
	@ApiOperation(value = "", nickname = "deleteRoleById")
	public void deleteById(@PathVariable Integer roleId) {
		roleService.deleteById(roleId);
	}

	@AllowAdmin
	@GetMapping("/{roleId}/users")
	@ApiOperation(value = "", nickname = "getAllRoleUsers")
	public ResponseEntity<List<User>> getAllUsers(@PathVariable Integer roleId) {
		return ResponseEntity.ok(roleService.findAllUsersById(roleId));
	}
}

