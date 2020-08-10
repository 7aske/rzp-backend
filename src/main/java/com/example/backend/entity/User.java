package com.example.backend.entity;

import com.example.backend.entity.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "user")
@Getter @Setter @NoArgsConstructor
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "user_username")
	private String userUsername;
	@Column(name = "user_email")
	private String userEmail;
	@Column(name = "user_password")
	private String userPassword;
	@Column(name = "user_first_name")
	private String userFirstName;
	@Column(name = "user_last_name")
	private String userLastName;
	@Column(name = "user_address")
	private String userAddress;
	@Column(name = "user_about")
	private String userAbout;
	@Column(name = "user_display_name")
	private String userDisplayName;
	@Column(name = "user_date_created")
	private LocalDate userDateCreated;
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name= "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private List<Role> userRoles = new ArrayList<>();
}