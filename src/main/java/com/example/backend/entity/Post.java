package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.example.backend.entity.Tag;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_post")
	private Long idPost;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	@JsonIgnore
	private User idUser;

	@JoinColumn(name = "id_category", referencedColumnName = "id_category")
	@ManyToOne
	private Category idCategory;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "id_post"), inverseJoinColumns = @JoinColumn(name = "id_tag"))
	private List<Tag> tagList;

	@Column(name = "post_title")
	private String postTitle;
	@Column(name = "post_excerpt")
	private String postExcerpt;
	@Column(name = "post_body")
	private String postBody;
	@Column(name = "post_slug")
	private String postSlug;
	@Column(name = "post_date_posted")
	private LocalDate postDatePosted = LocalDate.now();
	@Column(name = "post_published")
	private Boolean postPublished = true;
	@Column(name = "post_views")
	private Long postViews = 0L;
}