
package com.example.backend.controller;

import com.example.backend.entity.Role;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Role>> getAll() {
		return ResponseEntity.ok(roleService.findAll());
	}

	@GetMapping("/getById/{idRole}")
	public ResponseEntity<Role> getById(@PathVariable Long idRole) {
		return ResponseEntity.ok(roleService.findById(idRole));
	}

	@GetMapping("/getById/{roleName}")
	public ResponseEntity<Role> getById(@PathVariable String roleName) {
		return ResponseEntity.ok(roleService.findAllByRoleName(roleName));
	}

	@PostMapping("/save")
	public ResponseEntity<Role> save(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.save(role));
	}

	@PutMapping("/update")
	public ResponseEntity<Role> update(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.update(role));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Role role) {
		try {
			roleService.delete(role);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idRole}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idRole) {
		try {
			roleService.deleteById(idRole);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{roleName}")
	public ResponseEntity<Object> deleteById(@PathVariable String roleName) {
		try {
			roleService.deleteByRoleName(roleName);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

}
