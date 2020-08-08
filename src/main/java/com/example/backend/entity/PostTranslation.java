package com.example.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "post_translation")
@Getter @Setter @NoArgsConstructor @ToString
public class PostTranslation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_post_translation")
	private Long idPostTranslation;

	@ManyToOne
	@JoinColumn(name = "id_post", referencedColumnName = "id_post")
	private Post idPost;

	@ManyToOne
	@JoinColumn(name = "id_language", referencedColumnName = "id_language")
	private Language language;

	@Column(name = "post_title_translation")
	private String postTitleTranslation;
	@Column(name = "post_excerpt_translation")
	private String postExcerptTranslation;
	@Column(name = "post_body_translation")
	private String postBodyTranslation;
	@Column(name = "post_slug_translation")
	private String postSlugTranslation;
}
