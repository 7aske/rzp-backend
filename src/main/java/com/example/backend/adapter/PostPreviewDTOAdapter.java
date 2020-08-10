package com.example.backend.adapter;

import com.example.backend.entity.Post;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostPreviewDTO;

import java.util.ArrayList;

public class PostPreviewDTOAdapter {
	public static PostPreviewDTO adapt(Post post) {
		if (post == null) {
			return null;
		}

		PostPreviewDTO postDTO = new PostPreviewDTO();

		postDTO.setIdPost(post.getIdPost());
		postDTO.setIdUser(post.getIdUser().getIdUser());
		postDTO.setCategoryName(post.getIdCategory().getCategoryName());
		postDTO.setPostDatePosted(post.getPostDatePosted());
		postDTO.setPostViews(post.getPostViews());
		postDTO.setPostAuthor(post.getIdUser().getUserDisplayName());

		postDTO.setPostTitle(post.getPostTitle());
		postDTO.setPostSlug(post.getPostSlug());
		postDTO.setPostExcerpt(post.getPostExcerpt());
		postDTO.setTags(post.getTagList());
		return postDTO;
	}
}
