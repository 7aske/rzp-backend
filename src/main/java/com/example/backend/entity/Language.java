package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "language")
@Getter @Setter @NoArgsConstructor
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_language")
	private Long idLanguage;

	@Column(name = "language_name")
	private String languageName;
	@Column(name =  "language_date_format")
	private String languageDateFormat;
	@Column(name = "language_currency")
	private String languageCurrency;
}
