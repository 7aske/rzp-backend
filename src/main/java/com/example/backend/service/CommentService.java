package com.example.backend.service;

import com.example.backend.entity.Comment;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {

	List<Comment> findAll();

	boolean delete(Comment comment);

	Comment save(Comment comment);

	Comment update(Comment comment);

	Comment findById(Long idComment);

	List<Comment> findAllByIdUser(User idUser);

	List<Comment> findAllByIdPost(Post idPost);

	boolean deleteById(Long idComment);

	boolean deleteAllByIdUser(User idUser);

	boolean deleteAllByIdPost(Post idPost);

}
