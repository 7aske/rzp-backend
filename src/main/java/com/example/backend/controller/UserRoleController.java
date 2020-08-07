
package com.example.backend.controller;

import com.example.backend.entity.UserRole;
import com.example.backend.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/getAll")
	public ResponseEntity<List<UserRole>> getAll() {
		return ResponseEntity.ok(userRoleService.findAll());
	}

	@GetMapping("/getById/{idRole}/{idUser}")
	public ResponseEntity<UserRole> getById(@PathVariable Long idRole, @PathVariable Long idUser) {
		return ResponseEntity.ok(userRoleService.findByIdRoleAndIdUser(idRole, idUser));
	}


	@PostMapping("/save")
	public ResponseEntity<UserRole> save(@RequestBody UserRole userRole) {
		return ResponseEntity.ok(userRoleService.save(userRole));
	}

	@PutMapping("/update")
	public ResponseEntity<UserRole> update(@RequestBody UserRole userRole) {
		return ResponseEntity.ok(userRoleService.update(userRole));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody UserRole userRole) {
		return ResponseEntity.ok(userRoleService.delete(userRole));
	}

	@DeleteMapping("/deleteById/{idRole}/{idUser}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idRole, @PathVariable Long idUser) {
		return ResponseEntity.ok(userRoleService.deleteByIdRoleAndIdUser(idRole, idUser));
	}
}
