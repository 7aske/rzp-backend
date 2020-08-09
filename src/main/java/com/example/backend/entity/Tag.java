package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tag")
@Getter @Setter @NoArgsConstructor
public class Tag implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tag")
	private Long idTag;

	@Column(name = "tag_name")
	private String tagName;

	@JsonIgnore
	@ToString.Exclude
	@ManyToMany(mappedBy = "tagList")
	private List<Post> postList;
}