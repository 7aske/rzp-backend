package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.service.impl.PostServiceImpl;

import java.util.List;

public interface PostService {

	List<Post> findAll();

	void delete(Post post);

	Post save(PostDTO post) throws PostServiceImpl.PostValidationException;

	Post update(PostDTO post) throws PostServiceImpl.PostValidationException;

	Post findById(Long idPost);

	Post findByPostSlug(String postSlug, String locale);

	PostDTO findDTOByPostSlug(String postSlug, String locale);

	Post findAllByPostTitle(String postTitle, String locale);

	List<Post> findAllByIdUser(User idUser);

	List<Post> findAllByIdCategory(Category idCategory);

	List<Post> findAllByPostDeleted(Boolean postDeleted);

	List<Post> findAllByPostPublished(Boolean postPublished);

	List<PostDTO> findAllDTOByPostPublishedAndLocale(Boolean postPublished, String locale);

	void deleteById(Long idPost) throws Exception;

	void deleteAllByIdUser(User idUser) throws Exception;

	void deleteAllByIdCategory(Category idCategory) throws Exception;

	void deleteAllByPostDeleted(Boolean postDeleted) throws Exception;

	void deleteAllByPostPublished(Boolean postPublished) throws Exception;

}
