package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Blog post
 */
@Data
@Entity
@Table(name = "post")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Post extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "post_id")
	private Integer id;
	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "category_fk", referencedColumnName = "category_id")
	@ManyToOne
	private Category category;
	@Column(name = "title")
	private String title;
	@Column(name = "excerpt")
	private String excerpt;
	@Column(name = "body")
	private String body;
	@Column(name = "date_posted")
	private LocalDateTime datePosted;
	@Column(name = "deleted")
	private boolean deleted;
	@Column(name = "published")
	private boolean published;
	@Column(name = "views")
	private Integer views;
	@Column(name = "slug")
	private String slug;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "post_media", joinColumns = @JoinColumn(name = "media_fk"), inverseJoinColumns = @JoinColumn(name = "post_fk"))
	private List<Media> medias;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "tag_fk"), inverseJoinColumns = @JoinColumn(name = "post_fk"))
	private List<Tag> tags;

}