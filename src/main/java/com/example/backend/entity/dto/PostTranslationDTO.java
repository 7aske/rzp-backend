package com.example.backend.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostTranslationDTO {
	private Long idPostTranslation;

	private Long idPost;

	private String locale;

	private String postTitleTranslation;
	private String postExcerptTranslation;
	private String postBodyTranslation;
	private String postSlugTranslation;
}
