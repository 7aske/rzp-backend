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
@IdClass(PostTag.class)
@Table(name = "post_tag")
@Getter @Setter @NoArgsConstructor
public class PostTag implements Serializable {
	@Id
	@Column(name = "id_tag")
	private Long idTag;

	@Id
	@Column(name = "id_post")
	private Long idPost;
}