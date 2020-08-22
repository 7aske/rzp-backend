package com.example.backend.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
	private Long idUser;
	private String userUsername;
	private String userEmail;
	private String userFirstName;
	private String userLastName;
	private String userAddress;
	private String userAbout;
	private String userDisplayName;
	private Date userDateCreated;
	private List<String> userRoles;
	private Boolean userActive;
}
