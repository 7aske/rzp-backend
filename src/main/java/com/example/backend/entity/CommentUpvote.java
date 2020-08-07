package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "comment_upvote")
@Getter @Setter @NoArgsConstructor
public class CommentUpvote implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_upvote_hash")
	private String commentUpvoteHash;

	@JoinColumn(name = "id_comment", referencedColumnName = "id_comment")
	@ManyToOne
	private Comment idComment;
}