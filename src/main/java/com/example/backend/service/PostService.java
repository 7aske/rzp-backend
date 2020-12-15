package com.example.backend.service;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;

import java.util.List;

public interface PostService {

	List<Post> findAll();

	Post save(Post post);

	Post update(Post post);

	Post findById(Integer postId);

	void deleteById(Integer postId);

	List<Media> findAllMediasById(Integer postId);

	List<Tag> findAllTagsById(Integer postId);

}