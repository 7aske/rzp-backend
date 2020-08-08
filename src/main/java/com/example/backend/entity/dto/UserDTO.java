package com.example.backend.entity.dto;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
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
	private LocalDate userDateCreated;
	private List<Role> userRoles;

	public UserDTO(User user) {
		this.idUser = user.getIdUser();
		this.userUsername = user.getUserUsername();
		this.userEmail = user.getUserEmail();
		this.userFirstName = user.getUserFirstName();
		this.userLastName = user.getUserLastName();
		this.userAddress = user.getUserAddress();
		this.userAbout = user.getUserAddress();
		this.userDisplayName = user.getUserDisplayName();
		this.userDateCreated = user.getUserDateCreated();
		this.userRoles = user.getUserRoles();
	}
}