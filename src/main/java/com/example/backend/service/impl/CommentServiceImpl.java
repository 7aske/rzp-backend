package com.example.backend.service.impl;

import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Comment;
import com.example.backend.repository.CommentRepository;
import com.example.backend.service.CommentService;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment findById(Long idComment) {
		if (commentRepository.findById(idComment).isPresent()) {
			return commentRepository.findById(idComment).get();
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> findAllByIdUser(User idUser) {
		return commentRepository.findAllByIdUser(idUser);
	}

	@Override
	public List<Comment> findAllByIdPost(Post idPost) {
		return commentRepository.findAllByIdPost(idPost);
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment update(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public boolean delete(Comment comment) {
		commentRepository.delete(comment);
		return !commentRepository.findById(comment.getIdComment()).isPresent();
	}

	@Override
	public boolean deleteById(Long idComment) {
		commentRepository.deleteById(idComment);
		return !commentRepository.findById(idComment).isPresent();
	}

	@Override
	public boolean deleteAllByIdUser(User idUser) {
		return commentRepository.deleteAllByIdUser(idUser);
	}

	@Override
	public boolean deleteAllByIdPost(Post idPost) {
		return commentRepository.deleteAllByIdPost(idPost);
	}

}
