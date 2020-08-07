package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "comment")
@Getter @Setter @NoArgsConstructor
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment")
	private Long idComment;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	private User idUser;

	@JoinColumn(name = "id_post", referencedColumnName = "id_post")
	@ManyToOne
	private Post idPost;

	@Column(name = "comment_body")
	private String commentBody;
	@Column(name = "comment_date_posted")
	private LocalDate commentDatePosted;
}