package com.example.backend.service;

import com.example.backend.entity.Comment;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.CommentDTO;
import com.example.backend.service.impl.CommentServiceImpl;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {

	List<Comment> findAll();

	void delete(Comment comment) throws Exception;

	Comment save(CommentDTO comment) throws CommentServiceImpl.CommentValidationException;

	Comment update(Comment comment);

	CommentDTO findById(Long idComment);

	List<CommentDTO> findAllByIdUser(User idUser);

	List<CommentDTO> findAllByIdPost(Post idPost);

	List<CommentDTO> findAllByIdPostIdPost(Long idPost);

	List<CommentDTO> findAllByIdPostIdPost(Long idPost, Integer pageNumber, Integer count);

	void deleteById(Long idComment) throws Exception;

	void deleteAllByIdUser(User idUser) throws Exception;

	void deleteAllByIdPost(Post idPost) throws Exception;

}
