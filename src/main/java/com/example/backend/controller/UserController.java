
package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/getById/{idUser}")
	public ResponseEntity<User> getById(@PathVariable Long idUser) {
		return ResponseEntity.ok(userService.findById(idUser));
	}

	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.ok(userService.update(user));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody User user) {
		try {
			userService.delete(user);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idUser}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idUser) {
		try {
			userService.deleteById(idUser);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
