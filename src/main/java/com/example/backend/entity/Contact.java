package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "contact")
@Getter @Setter @NoArgsConstructor
public class Contact implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contact")
	private Long idContact;

	@JoinColumn(name = "id_contact_type", referencedColumnName = "id_contact_type")
	@ManyToOne
	private ContactType idContactType;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	private User idUser;

	@Column(name = "contact_value")
	private String contactValue;
}