
package com.example.backend.controller.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.dto.UserDTO;
import com.example.backend.entity.dto.UserPropertyUpdateDTO;
import com.example.backend.entity.dto.UserUpdatePasswordDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.security.JWTUtils;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user/user")
public class UserUserController {
	@Autowired
	private UserService userService;

	@PutMapping("/updateProperty")
	public ResponseEntity<Object> updateProperty(@RequestBody UserPropertyUpdateDTO updateDTO, HttpServletRequest request) {
		try {
			Long idUser = JWTUtils.getIdUserFromRequest(request);
			UserDTO updated = userService.updateProperty(idUser, updateDTO.getProperty(), updateDTO.getValue());
			return ResponseEntity.ok(updated);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<Object> updatePassword(@RequestBody UserUpdatePasswordDTO passwordDTO, HttpServletRequest request) {
		try {
			Long idUser = JWTUtils.getIdUserFromRequest(request);
			UserDTO updated = userService.updatePassword(idUser,
					passwordDTO.getPassword(),
					passwordDTO.getConfirmPassword(),
					passwordDTO.getNewPassword());
			return ResponseEntity.ok(updated);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
