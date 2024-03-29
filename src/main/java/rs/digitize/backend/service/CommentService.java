package rs.digitize.backend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.Comment;
import rs.digitize.backend.entity.Post;

import java.util.List;

public interface CommentService {

	List<Comment> findAll(Integer postId, Specification<Comment> specification, Pageable pageable);

	List<Comment> findAllForPost(Post post);

	Comment save(Integer postId, Comment comment);

	Comment update(Comment comment);

	Comment findById(Integer commentId);

	void deleteById(Integer commentId);

}