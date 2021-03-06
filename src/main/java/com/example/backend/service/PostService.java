package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostPreviewDTO;
import com.example.backend.service.impl.PostServiceImpl;

import java.util.List;

public interface PostService {

	Integer getPageCount(Long idUser, String categoryName, String tagName, Boolean published, Integer size);

	List<PostDTO> findAll(String categoryName, String tagName, Integer pageNumber, Integer count, Boolean published);

	List<PostPreviewDTO> findAllPreviews(String categoryName, String tagName, Integer pageNumber, Integer count, Boolean published);

	void delete(Post post) throws Exception;

	PostDTO save(PostDTO post) throws PostServiceImpl.PostValidationException;

	PostDTO update(PostDTO post) throws PostServiceImpl.PostValidationException;

	PostDTO findById(Long idPost);

	PostDTO findByPostSlug(String postSlug);

	List<PostDTO> findAllByIdUser(Long idUser, String category, Integer pageNumber, Integer count, Boolean published);

	void deleteById(Long idPost) throws Exception;
}
