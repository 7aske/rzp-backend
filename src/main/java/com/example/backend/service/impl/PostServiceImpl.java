package com.example.backend.service.impl;

import com.example.backend.adapter.PostAdapter;
import com.example.backend.adapter.PostPreviewDTOAdapter;
import com.example.backend.entity.*;
import com.example.backend.entity.dto.*;
import com.example.backend.repository.*;
import com.example.backend.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.backend.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService, UserPostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private final Integer PAGE_SIZE = 10;

	@Override
	public Integer getPageCount(Long idUser, String categoryName, String tagName, Boolean published, Integer size) {
		if (size == null) {
			size = PAGE_SIZE;
		}

		if (published != null) {
			if (idUser != null && categoryName != null) {
				return (int) Math.ceil(postRepository.countUserCategoryPostsPublished(idUser, categoryName, published) / (double) size);
			} else if (idUser != null) {
				return (int) Math.ceil(postRepository.countUserPostsPublished(idUser, published) / (double) size);
			} else if (categoryName != null) {
				return (int) Math.ceil(postRepository.countCategoryPostsPublished(categoryName, published) / (double) size);
			} else if (tagName != null) {
				return (int) Math.ceil(postRepository.countTagPostsPublished(tagName, published));
			} else {
				return (int) Math.ceil(postRepository.countPostsPublished(published) / (double) size);
			}
		} else {
			if (idUser != null && categoryName != null) {
				return (int) Math.ceil(postRepository.countUserCategoryPosts(idUser, categoryName) / (double) size);
			} else if (idUser != null) {
				return (int) Math.ceil(postRepository.countUserPosts(idUser) / (double) size);
			} else if (categoryName != null) {
				return (int) Math.ceil(postRepository.countCategoryPosts(categoryName) / (double) size);
			} else if (tagName != null) {
				return (int) Math.ceil(postRepository.countTagPosts(tagName));
			} else {
				return (int) Math.ceil(postRepository.countPosts() / (double) size);
			}
		}
	}

	@Override
	public List<PostDTO> findAll(Long idUser, String categoryName, String tagName, Integer pageNumber, Integer count, Boolean published) {
		return findAll(categoryName, tagName, pageNumber, count, published)
				.stream()
				.filter(p -> p.getIdUser().equals(idUser))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostPreviewDTO> findAllPreviews(Long idUser, String categoryName, String tagName, Integer pageNumber, Integer count, Boolean published) {
		return findAllPreviews(categoryName, tagName, pageNumber, count, published)
				.stream()
				.filter(p -> p.getIdUser().equals(idUser))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Long idUser, Post post) throws Exception {
		if (post.getIdUser().getIdUser().equals(idUser)) {
			delete(post);
			return;
		}
		throw new PostValidationException("post.delete.unauthorized");
	}

	@Override
	public PostDTO save(Long idUser, PostDTO post) throws PostValidationException {
		if (post.getIdUser().equals(idUser)) {
			return save(post);
		}
		throw new PostValidationException("post.save.unauthorized");
	}

	@Override
	public PostDTO update(Long idUser, PostDTO post) throws PostValidationException {
		if (post.getIdUser().equals(idUser)) {
			return update(post);
		}
		throw new PostValidationException("post.update.unauthorized");
	}

	@Override
	public void deleteById(Long idUser, Long idPost) throws Exception {
		Post post = postRepository.findByIdPost(idPost).orElse(null);
		if (post == null) {
			return;
		}

		if (post.getIdUser().getIdUser().equals(idUser)) {
			delete(post);
		}
		throw new PostValidationException("post.delete.unauthorized");
	}

	@Override
	public List<PostDTO> findAll(String categoryName, String tagName, Integer pageNumber, Integer pageSize, Boolean published) {
		return getPostsByQuery(categoryName, tagName, pageNumber, pageSize, published)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostPreviewDTO> findAllPreviews(String categoryName, String tagName, Integer pageNumber, Integer pageSize, Boolean published) {
		return getPostsByQuery(categoryName, tagName, pageNumber, pageSize, published)
				.stream()
				.map(PostPreviewDTOAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public PostDTO findById(Long idPost) {
		return PostAdapter.adapt(postRepository.findById(idPost).orElse(null));
	}

	@Override
	public PostDTO findByPostSlug(String postSlug) {
		return PostAdapter.adapt(postRepository.findByPostSlug(postSlug).orElse(null));
	}

	@Override
	public List<PostDTO> findAllByIdUser(Long idUser, String categoryName, Integer pageNumber, Integer pageSize, Boolean published) {
		return findAll(categoryName, null, pageNumber, pageSize, published)
				.stream()
				.filter(p -> p.getIdUser().equals(idUser))
				.collect(Collectors.toList());
	}

	@Override
	public PostDTO save(PostDTO postDTO) throws PostValidationException {
		validatePost(postDTO);

		boolean isSlugValid = !postRepository.findByPostSlug(postDTO.getPostSlug().toLowerCase()).isPresent();
		if (!isSlugValid) {
			throw new PostValidationException("post.save.slug-exists");
		}

		Post post = new Post();
		Category category = categoryRepository.findByIdCategory(postDTO.getIdCategory()).orElse(null);
		User user = userRepository.findById(postDTO.getIdUser()).orElse(null);

		post.setPostTitle(postDTO.getPostTitle());
		post.setPostSlug(postDTO.getPostSlug().toLowerCase());
		post.setPostExcerpt(postDTO.getPostExcerpt());
		post.setPostBody(postDTO.getPostBody());
		post.setTagList(postDTO.getTags());

		post.setIdCategory(category);
		post.setIdUser(user);

		return PostAdapter.adapt(postRepository.save(post));
	}

	@Override
	public PostDTO update(PostDTO postDTO) throws PostValidationException {
		validatePost(postDTO);

		Post post = postRepository.findByIdPost(postDTO.getIdPost())
				.orElseThrow(() -> new PostValidationException("post.save.post-not-found"));

		Category category = categoryRepository.findByIdCategory(postDTO.getIdCategory()).orElse(null);

		boolean isSlugValid = post.getPostSlug().equals(postDTO.getPostSlug()) ||
				!postRepository.findByPostSlug(post.getPostSlug()).isPresent();
		if (!isSlugValid) {
			throw new PostValidationException("post.save.slug-exists");
		}

		post.setIdCategory(category);
		post.setPostPublished(postDTO.getPostPublished());
		post.setPostDateUpdated(new Date());

		post.setPostTitle(postDTO.getPostTitle());
		post.setPostSlug(postDTO.getPostSlug());
		post.setPostExcerpt(postDTO.getPostExcerpt());
		post.setPostBody(postDTO.getPostBody());
		post.setTagList(postDTO.getTags());

		post.setIdCategory(category);

		return PostAdapter.adapt(postRepository.save(post));
	}

	@Override
	public void delete(Post post) throws Exception {
		postRepository.delete(post);
	}

	@Override
	public void deleteById(Long idPost) throws Exception {
		postRepository.deleteById(idPost);
	}

	private Page<Post> getPostsByQuery(String categoryName, String tagName, Integer pageNumber, Integer pageSize, Boolean published) {
		Pageable page = getPage(pageNumber, pageSize);
		if (categoryName != null && published != null) {
			return postRepository.findAllByIdCategoryCategoryNameAndPostPublishedOrderByPostDatePostedDesc(categoryName, published, page);
		} else if (tagName != null && published != null) {
			return postRepository.findAllByTagNameAndPostPublished(tagName, published, page);
		} else if (categoryName != null) {
			return postRepository.findAllByIdCategoryCategoryNameOrderByPostDatePostedDesc(categoryName, page);
		} else if (tagName != null) {
			return postRepository.findAllByTagName(tagName, page);
		} else if (published != null) {
			return postRepository.findAllByPostPublishedOrderByPostDatePostedDesc(published, page);
		} else {
			return postRepository.findAllByOrderByPostDatePostedDesc(page);
		}
	}

	private Pageable getPage(Integer pageNumber, Integer pageSize) {
		if (pageNumber == null) {
			pageNumber = 0;
		}

		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}

		return PageRequest.of(pageNumber, pageSize);
	}

	private void validatePost(PostDTO post) throws PostValidationException {

		if (post.getPostSlug() == null || post.getPostSlug().isEmpty()) {
			throw new PostValidationException("post.save.slug-empty");
		}

		if (post.getPostTitle() == null || post.getPostTitle().isEmpty()) {
			throw new PostValidationException("post.save.title-empty");
		}

		if (post.getPostBody() == null || post.getPostBody().isEmpty()) {
			throw new PostValidationException("post.save.body-empty");
		}

		if (post.getPostExcerpt() == null || post.getPostExcerpt().isEmpty()) {
			throw new PostValidationException("post.save.excerpt-empty");
		}

		if (post.getIdCategory() == null) {
			throw new PostValidationException("post.save.category-invalid");
		}

		if (!categoryRepository.findByIdCategory(post.getIdCategory()).isPresent()) {
			throw new PostValidationException("post.save.category-not-found");
		}
	}

	public static class PostValidationException extends Exception {
		public PostValidationException(String message) {
			super(message);
		}
	}
}
