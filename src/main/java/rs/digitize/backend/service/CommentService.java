package rs.digitize.backend.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.*;

public interface CommentService {

	List<Comment> findAll(Integer postId, Specification<Comment> specification, Pageable pageable);

	Comment save(Integer postId, Comment comment);

	Comment update(Comment comment);

	Comment findById(Integer commentId);

	void deleteById(Integer commentId);

}