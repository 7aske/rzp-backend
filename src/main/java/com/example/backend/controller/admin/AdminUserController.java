
package com.example.backend.controller.admin;

import com.example.backend.adapter.UserAdapter;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.UserDTO;
import com.example.backend.entity.dto.UserPropertyUpdateDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.security.JWTUtils;
import com.example.backend.service.UserService;
import com.example.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
	@Autowired
	private UserService userService;

	@PutMapping("/updateProperty")
	public ResponseEntity<Object> updateProperty(@RequestBody UserPropertyUpdateDTO updateDTO) {
		try {
			UserDTO updated = userService.updateProperty(updateDTO.getIdUser(), updateDTO.getProperty(), updateDTO.getValue());
			return ResponseEntity.ok(updated);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<UserDTO>> getAll() {
		return ResponseEntity.ok(userService.findAll()
		.stream()
		.map(UserAdapter::adapt)
		.collect(Collectors.toList()));
	}

	@GetMapping("/getById/{idUser}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long idUser) {
		return ResponseEntity.ok(userService.findById(idUser));
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody User user) {
		try {
			return ResponseEntity.ok(userService.save(user));
		} catch (UserServiceImpl.UserValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody UserDTO user) {
		try {
			return ResponseEntity.ok(userService.update(user));
		} catch (UserServiceImpl.UserValidationException e) {
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
