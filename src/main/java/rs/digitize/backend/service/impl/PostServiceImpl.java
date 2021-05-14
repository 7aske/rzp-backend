package rs.digitize.backend.service.impl;

import rs.digitize.backend.entity.Post;
import rs.digitize.backend.entity.Tag;
import rs.digitize.backend.repository.PostRepository;
import rs.digitize.backend.service.PostService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findAll(Specification<Post> specification, Sort sort, Pageable pageable) {
		sort = sort == null ? Sort.unsorted() : sort;
		if (pageable != null) {
			return postRepository.findAll(specification, pageable).toList();
		}
		return postRepository.findAll(specification, sort);
	}

	@Override
	public Long count(Specification<Post> query) {
		return postRepository.count(query);
	}

	@Override
	public Post findById(Integer postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new NoSuchElementException("PostService.notFound"));
	}

	@Override
	public Post findBySlug(String slug) {
		return postRepository.findBySlug(slug)
				.orElseThrow(() -> new NoSuchElementException("PostService.notFound"));
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post update(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void deleteById(Integer postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public List<Tag> findAllTagsById(Integer postId) {
		return findById(postId).getTags();
	}
}