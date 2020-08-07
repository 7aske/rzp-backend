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
@IdClass(PostMedia.class)
@Table(name = "post_media")
@Getter @Setter @NoArgsConstructor
public class PostMedia implements Serializable {
	@Id
	@Column(name = "id_media")
	private Long idMedia;

	@Id
	@Column(name = "id_post")
	private Long idPost;
}