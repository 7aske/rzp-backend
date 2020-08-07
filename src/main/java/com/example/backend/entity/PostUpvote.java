package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "post_upvote")
@Getter @Setter @NoArgsConstructor
public class PostUpvote implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_upvote_hash")
	private String postUpvoteHash;

	@JoinColumn(name = "id_post", referencedColumnName = "id_post")
	@ManyToOne
	private Post idPost;
}