package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostPreviewDTO;
import com.example.backend.service.impl.PostServiceImpl;

import java.util.List;

public interface UserPostService {

	Integer getPageCount(Long idUser, String categoryName, Boolean published, Integer size);

	List<PostDTO> findAll(Long idUser, String category, Integer pageNumber, Integer count, Boolean published);

	List<PostPreviewDTO> findAllPreviews(Long idUser, String category, Integer pageNumber, Integer count, Boolean published);

	void delete(Long idUser, Post post) throws Exception;

	PostDTO save(Long idUser, PostDTO post) throws PostServiceImpl.PostValidationException;

	PostDTO update(Long idUser, PostDTO post) throws PostServiceImpl.PostValidationException;

	void deleteById(Long idUser, Long idPost) throws Exception;
}
