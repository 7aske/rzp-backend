package com.example.backend.entity.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommentDTO {
	private Long idUser;
	private String userUsername;
	private String userEmail;
	private String userFirstName;
	private String userLastName;
	private String userDisplayName;
}
