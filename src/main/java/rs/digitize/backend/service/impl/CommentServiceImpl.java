package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.Comment;
import rs.digitize.backend.entity.Post;
import rs.digitize.backend.repository.CommentRepository;
import rs.digitize.backend.service.CommentService;
import rs.digitize.backend.service.PostService;

import java.util.List;
import java.util.NoSuchElementException;

import static rs.digitize.backend.search.SearchOperation.IS_NULL;
import static rs.digitize.backend.util.PageRequestUtil.overrideSort;
import static rs.digitize.backend.util.SpecificationUtil.combineSpecificationFor;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final PostService postService;

	@Override
	public List<Comment> findAll(Integer postId, Specification<Comment> specification, Pageable pageable) {
		specification = combineSpecificationFor(specification, "post.id", postId);
		specification = combineSpecificationFor(specification, "parent", IS_NULL);
		Sort defaultSort = Sort.by("createdDate").descending();
		pageable = overrideSort(pageable, defaultSort);
		if (pageable == null)
			return commentRepository.findAll(specification, defaultSort);
		return commentRepository.findAll(specification, pageable).toList();
	}

	@Override
	public Comment findById(Integer commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new NoSuchElementException("CommentService.notFound"));
	}

	@Override
	public Comment save(Integer postId, Comment comment) {
		Post post = postService.findById(postId);
		comment.setPost(post);
		return commentRepository.save(comment);
	}

	@Override
	public Comment update(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteById(Integer commentId) {
		commentRepository.deleteById(commentId);
	}
}