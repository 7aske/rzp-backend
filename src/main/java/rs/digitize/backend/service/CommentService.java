package rs.digitize.backend.service;

import rs.digitize.backend.entity.Comment;

import java.util.List;

public interface CommentService {

	List<Comment> findAll();

	Comment save(Comment comment);

	Comment update(Comment comment);

	Comment findById(Integer commentId);

	void deleteById(Integer commentId);

}