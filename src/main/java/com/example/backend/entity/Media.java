package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Uploaded image shown on the blog post
 */
@Data
@Entity
@Table(name = "media")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Media extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "media_id")
	private Integer id;
	@Column(name = "uri")
	private String uri;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "post_media", joinColumns = @JoinColumn(name = "post_fk"), inverseJoinColumns = @JoinColumn(name = "media_fk"))
	private List<Post> posts;

}