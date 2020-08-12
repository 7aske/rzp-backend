package com.example.backend.adapter;

import com.example.backend.entity.Post;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostPreviewDTO;

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
		postDTO.setPostDateUpdated(post.getPostDateUpdated());
		postDTO.setPostPublished(post.getPostPublished());
		postDTO.setPostViews(post.getPostViews());
		postDTO.setPostAuthor(post.getIdUser().getUserDisplayName());

		postDTO.setPostTitle(post.getPostTitle());
		postDTO.setPostSlug(post.getPostSlug());
		postDTO.setPostExcerpt(post.getPostExcerpt());
		postDTO.setPostBody(post.getPostBody());
		postDTO.setTags(post.getTagList());
		return postDTO;
	}




}
