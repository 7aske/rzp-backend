package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.service.impl.PostServiceImpl;

import java.util.List;

public interface PostService {

	List<PostDTO> findAll();

	void delete(Post post) throws Exception;

	PostDTO save(PostDTO post) throws PostServiceImpl.PostValidationException;

	PostDTO update(PostDTO post) throws PostServiceImpl.PostValidationException;

	PostDTO findById(Long idPost);

	PostDTO findByPostSlug(String postSlug);

	List<PostDTO> findAllByIdUser(User idUser);

	List<PostDTO> findAllByIdCategory(Category idCategory);

	List<PostDTO> findAllByIdCategoryCategoryName(String categoryName);

	List<PostDTO> findAllByPostPublished(Boolean postPublished);

	List<PostDTO> findAllByPostPublishedTrue();

	List<PostDTO> findAllDTOByPostPublishedTrueAndCategoryName(String categoryName);

	void deleteById(Long idPost) throws Exception;
}
