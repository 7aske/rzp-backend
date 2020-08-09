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
		postDTO.setPostPublished(post.getPostPublished());
		postDTO.setPostViews(post.getPostViews());

		postDTO.setPostTitle(post.getPostTitle());
		postDTO.setPostSlug(post.getPostSlug());
		postDTO.setPostExcerpt(post.getPostExcerpt());
		postDTO.setPostBody(post.getPostBody());
		postDTO.setTagList(post.getTagList());
		return postDTO;
	}

}
