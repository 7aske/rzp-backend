package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.entity.PostTag;
import com.example.backend.entity.Tag;

import java.util.List;

public interface PostTagService {

	List<PostTag> findAll();

	boolean delete(PostTag postTag);

	PostTag save(PostTag postTag);

	PostTag update(PostTag postTag);


	PostTag findByIdTagAndIdPost(Long idTag, Long idPost);

	List<PostTag> findAllByIdTag(Long idTag);

	List<PostTag> findAllByIdPost(Long idPost);


	boolean deleteByIdTagAndIdPost(Long idTag, Long idPost);

	boolean deleteAllByIdTag(Long idTag);

	boolean deleteAllByIdPost(Long idPost);
}
