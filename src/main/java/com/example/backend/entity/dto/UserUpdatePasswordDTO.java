package com.example.backend.entity.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserUpdatePasswordDTO {
	private String password;
	private String confirmPassword;
	private String newPassword;
}
