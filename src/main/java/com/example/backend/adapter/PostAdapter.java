package com.example.backend.adapter;

import com.example.backend.entity.Post;
import com.example.backend.entity.data.Locale;
import com.example.backend.entity.dto.PostDTO;

public class PostAdapter {
	public static PostDTO adapt(Post post) {
		if (post == null) {
			return null;
		}

		PostDTO postDTO = new PostDTO();

		postDTO.setIdPost(post.getIdPost());
		postDTO.setIdUser(post.getIdUser().getIdUser());
		postDTO.setIdCategory(post.getIdCategory().getIdCategory());
		postDTO.setPostDatePosted(post.getPostDatePosted());
		postDTO.setPostDeleted(post.getPostDeleted());
		postDTO.setPostPublished(post.getPostPublished());
		postDTO.setPostViews(post.getPostViews());

		postDTO.setPostTranslations(PostTranslationAdapter.adapt(post.getPostTranslations()));

		return postDTO;
	}

}
