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

	Post findByPostSlug(String postSlug);

	PostDTO findDTOByPostSlug(String postSlug);

	Post findAllByPostTitle(String postTitle);

	List<Post> findAllByIdUser(User idUser);

	List<Post> findAllByIdCategory(Category idCategory);

	List<Post> findAllByPostPublished(Boolean postPublished);

	List<PostDTO> findAllDTOByPostPublishedTrueAndCategoryName(String categoryName);

	List<PostDTO> findAllDTOByPostPublished(Boolean postPublished);

	void deleteById(Long idPost) throws Exception;

	void deleteAllByIdUser(User idUser) throws Exception;

	void deleteAllByIdCategory(Category idCategory) throws Exception;

	void deleteAllByPostPublished(Boolean postPublished) throws Exception;

}
