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
	public void delete(Comment comment) throws Exception {
		commentRepository.delete(comment);
	}

	@Override
	public void deleteById(Long idComment) throws Exception {
		commentRepository.deleteById(idComment);
	}

	@Override
	public void deleteAllByIdUser(User idUser) throws Exception {
		commentRepository.deleteAllByIdUser(idUser);
	}

	@Override
	public void deleteAllByIdPost(Post idPost) throws Exception {
		commentRepository.deleteAllByIdPost(idPost);
	}

}
