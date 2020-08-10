package com.example.backend.service.impl;

import com.example.backend.adapter.CommentAdapter;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.entity.dto.CommentDTO;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Comment;
import com.example.backend.repository.CommentRepository;
import com.example.backend.service.CommentService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public CommentDTO findById(Long idComment) {
		return CommentAdapter.adapt(commentRepository.findById(idComment).orElse(null));
	}

	@Override
	public List<CommentDTO> findAllByIdUser(User idUser) {
		return commentRepository.findAllByIdUser(idUser)
				.stream()
				.map(CommentAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<CommentDTO> findAllByIdPostIdPost(Long idPost) {
		return commentRepository.findAllByIdPostIdPost(idPost)
				.stream()
				.map(CommentAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<CommentDTO> findAllByIdPost(Post idPost) {
		return commentRepository.findAllByIdPost(idPost)
				.stream()
				.map(CommentAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public Comment save(CommentDTO commentDTO) throws CommentValidationException {
		validate(commentDTO);
		Comment comment = new Comment();
		comment.setIdComment(null);
		comment.setCommentBody(commentDTO.getCommentBody());
		comment.setCommentDatePosted(new Date());
		comment.setIdUser(userRepository.findById(commentDTO.getIdUser().getIdUser()).orElse(null));
		comment.setIdPost(postRepository.findById(commentDTO.getIdPost()).orElse(null));
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

	private void validate(CommentDTO commentDTO) throws CommentValidationException {
		if (commentDTO.getCommentBody() == null || commentDTO.getCommentBody().isEmpty()) {
			throw new CommentValidationException("comment.save.body-empty");
		}

		if (commentDTO.getIdUser() == null || !userRepository.findById(commentDTO.getIdUser().getIdUser()).isPresent()) {
			throw new CommentValidationException("comment.save.user-invalid");
		}

		if (commentDTO.getIdPost() == null || !postRepository.findById(commentDTO.getIdPost()).isPresent()) {
			throw new CommentValidationException("comment.save.post-not-found");
		}
	}

	public static class CommentValidationException extends Exception {
		public CommentValidationException(String message) {
			super(message);
		}
	}
}
