package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "media")
@Getter @Setter @NoArgsConstructor
public class Media implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_media")
	private Long idMedia;

	@Column(name = "media_filepath")
	private String mediaFilepath;
}