package com.example.backend.service;

import com.example.backend.entity.Comment;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {

	List<Comment> findAll();

	void delete(Comment comment) throws Exception;

	Comment save(Comment comment);

	Comment update(Comment comment);

	Comment findById(Long idComment);

	List<Comment> findAllByIdUser(User idUser);

	List<Comment> findAllByIdPost(Post idPost);

	void deleteById(Long idComment) throws Exception;

	void deleteAllByIdUser(User idUser) throws Exception;

	void deleteAllByIdPost(Post idPost) throws Exception;

}
