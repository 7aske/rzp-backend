package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_post")
	private Long idPost;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	private User idUser;

	@JoinColumn(name = "id_category", referencedColumnName = "id_category")
	@ManyToOne
	private Category idCategory;

	@Column(name = "post_title")
	private String postTitle;
	@Column(name = "post_excerpt")
	private String postExcerpt;
	@Column(name = "post_body")
	private String postBody;
	@Column(name = "post_date_posted")
	private LocalDate postDatePosted;
	@Column(name = "post_deleted")
	private Boolean postDeleted;
	@Column(name = "post_published")
	private Boolean postPublished;
	@Column(name = "post_views")
	private Long postViews;
	@Column(name = "post_slug")
	private String postSlug;
}