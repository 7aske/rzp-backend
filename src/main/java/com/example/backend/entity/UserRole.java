package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Embeddable
@IdClass(UserRole.class)
@Table(name = "user_role")
@Getter @Setter @NoArgsConstructor
public class UserRole implements Serializable {
	@Id
	@Column(name = "id_role")
	private Long idRole;

	@Id
	@Column(name = "id_user")
	private Long idUser;
}