package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "contact_type")
@Getter @Setter @NoArgsConstructor
public class ContactType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contact_type")
	private Long idContactType;

	@Column(name = "contact_type_name")
	private String contactTypeName;
	@Column(name = "contact_type_value_type")
	private String contactTypeValueType;

}