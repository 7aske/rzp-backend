
package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.dto.UserDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.UserService;
import com.example.backend.service.impl.UserServiceImpl;
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
	public ResponseEntity<UserDTO> getById(@PathVariable Long idUser) {
		return ResponseEntity.ok(userService.findById(idUser));
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody User user){
		try {
			return ResponseEntity.ok(userService.save(user));
		} catch (UserServiceImpl.UserValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
