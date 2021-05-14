package rs.digitize.backend.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.*;

public interface CommentService {

	List<Comment> findAll(Specification<Comment> specification, Sort sort);

	Comment save(Comment comment);

	Comment update(Comment comment);

	Comment findById(Integer commentId);

	void deleteById(Integer commentId);

}