package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.digitize.backend.data.PostSummary;
import rs.digitize.backend.entity.Post;
import rs.digitize.backend.entity.Tag;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.exception.InvalidPostSummaryTypeException;
import rs.digitize.backend.exception.http.HttpUnauthorizedException;
import rs.digitize.backend.repository.PostRepository;
import rs.digitize.backend.service.PostService;
import rs.digitize.backend.service.TagService;

import java.util.List;
import java.util.NoSuchElementException;

import static rs.digitize.backend.util.PageRequestUtil.overrideSort;
import static rs.digitize.backend.util.Sorts.CREATED_DATE_SORT;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@CacheConfig(cacheNames = {"post-count"})
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final TagService tagService;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findAll(Specification<Post> specification, Sort sort, Pageable pageable) {
		if (pageable != null) {
			pageable = overrideSort(pageable, pageable.getSort().and(CREATED_DATE_SORT));
			return postRepository.findAll(specification, pageable).toList();
		}
		return postRepository.findAll(specification, sort == null ? CREATED_DATE_SORT : sort);
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
		Post existing = findById(post.getId());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		post.setUser(existing.getUser());
		if (user != null && (user.isAdmin() || user.equals(existing.getUser()))) {
			return postRepository.save(post);
		} else {
			throw new HttpUnauthorizedException();
		}
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

	@Override
	public PostSummary getSummary(PostSummary.SummaryType type) {
		List<Post> posts = findAll();
		switch (type) {
			case TAG:
				List<Tag> tags = tagService.findAll(null, null);
				return PostSummary.builder()
						.posts(posts)
						.tags(tags)
						.build()
						.byTag();
			case CATEGORY:
				return PostSummary.builder()
						.posts(posts)
						.build()
						.byCategory();
			default:
				throw new InvalidPostSummaryTypeException();
		}
	}
}