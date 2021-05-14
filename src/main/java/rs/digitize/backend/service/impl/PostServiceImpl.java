package rs.digitize.backend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.*;
import rs.digitize.backend.repository.PostRepository;
import rs.digitize.backend.service.PostService;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@CacheConfig(cacheNames={"post-count"})
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findAll(Specification<Post> specification, Sort sort, Pageable pageable) {
		if (pageable != null) {
			return postRepository.findAll(specification, pageable).toList();
		}
		return postRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	@Cacheable("post-count")
	public Long count(Specification<Post> specification) {
		return postRepository.count(specification);
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
	@CacheEvict("post-count")
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post update(Post post) {
		return postRepository.save(post);
	}

	@Override
	@CacheEvict("post-count")
	public void deleteById(Integer postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public List<Tag> findAllTagsById(Integer postId) {
		return findById(postId).getTags();
	}

	@Override
	public List<Tag> addTagsById(Integer postId, List<Tag> tags) {
		Post post = findById(postId);
		post.getTags().addAll(tags);
		return postRepository.save(post).getTags();
	}

	@Override
	public List<Tag> setTagsById(Integer postId, List<Tag> tags) {
		Post post = findById(postId);
		post.setTags(tags);
		return postRepository.save(post).getTags();
	}

	@Override
	public List<Tag> deleteTagsById(Integer postId, List<Tag> tags) {
		Post post = findById(postId);
		post.getTags().removeAll(tags);
		return postRepository.save(post).getTags();
	}
}