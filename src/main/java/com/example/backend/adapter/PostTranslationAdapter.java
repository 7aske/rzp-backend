package com.example.backend.adapter;

import com.example.backend.entity.Language;
import com.example.backend.entity.PostTranslation;
import com.example.backend.entity.dto.PostTranslationDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PostTranslationAdapter {
	public static PostTranslation adapt(PostTranslationDTO postTranslationDTO) {
		if (postTranslationDTO == null) {
			return null;
		}

		PostTranslation postTranslation = new PostTranslation();

		postTranslation.setIdPostTranslation(postTranslationDTO.getIdPostTranslation());
		postTranslation.setPostBodyTranslation(postTranslationDTO.getPostBodyTranslation());
		postTranslation.setPostTitleTranslation(postTranslationDTO.getPostTitleTranslation());
		postTranslation.setPostExcerptTranslation(postTranslationDTO.getPostExcerptTranslation());
		postTranslation.setPostSlugTranslation(postTranslationDTO.getPostSlugTranslation());

		return postTranslation;
	}

	public static PostTranslationDTO adapt(PostTranslation postTranslation) {
		if (postTranslation == null) {
			return null;
		}

		PostTranslationDTO postTranslationDTO = new PostTranslationDTO();

		postTranslationDTO.setIdPostTranslation(postTranslation.getIdPostTranslation());
		postTranslationDTO.setIdPost(postTranslation.getIdPost().getIdPost());
		postTranslationDTO.setLocale(postTranslation.getLanguage().getLanguageName());
		postTranslationDTO.setPostBodyTranslation(postTranslation.getPostBodyTranslation());
		postTranslationDTO.setPostExcerptTranslation(postTranslation.getPostExcerptTranslation());
		postTranslationDTO.setPostTitleTranslation(postTranslation.getPostTitleTranslation());
		postTranslationDTO.setPostSlugTranslation(postTranslation.getPostSlugTranslation());

		return postTranslationDTO;
	}

	public static List<PostTranslationDTO> adapt(List<PostTranslation> postTranslations) {
		if (postTranslations == null) {
			return null;
		}

		return postTranslations
				.stream()
				.map(PostTranslationAdapter::adapt)
				.collect(Collectors.toList());
	}
}
