package com.example.backend.service.impl;

import com.example.backend.adapter.PostAdapter;
import com.example.backend.entity.*;
import com.example.backend.entity.data.Locale;
import com.example.backend.entity.dto.*;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Post findById(Long idPost) {
		return postRepository.findById(idPost).orElse(null);
	}

	@Override
	public Post findByPostSlug(String postSlug) {
		return postRepository.findByPostSlug(postSlug).orElse(null);
	}

	@Override
	public PostDTO findDTOByPostSlug(String postSlug) {
		return PostAdapter.adapt(postRepository.findByPostSlug(postSlug).orElse(null));
	}

	@Override
	public List<Post> findAllByIdUser(User idUser) {
		return postRepository.findAllByIdUser(idUser);
	}

	@Override
	public List<Post> findAllByIdCategory(Category idCategory) {
		return postRepository.findAllByIdCategory(idCategory);
	}

	@Override
	public Post findAllByPostTitle(String postTitle) {
		return postRepository.findByPostTitle(postTitle).orElse(null);
	}

	@Override
	public List<Post> findAllByPostPublished(Boolean postPublished) {
		return postRepository.findAllByPostPublished(postPublished);
	}

	@Override
	public List<PostDTO> findAllDTOByPostPublishedTrueAndCategoryName(String categoryName) {
		return postRepository.findAllByPostPublishedTrueAndIdCategoryCategoryName(categoryName)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAllDTOByPostPublished(Boolean postPublished) {
		return postRepository.findAllByPostPublished(postPublished)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public Post save(PostDTO postDTO) throws PostValidationException {
		validatePost(postDTO);

		boolean isSlugValid = !postRepository.findByPostSlug(postDTO.getPostSlug()).isPresent();
		if (!isSlugValid) {
			throw new PostValidationException("post.save.slug-exists");
		}

		Post post = new Post();
		Category category = categoryRepository.findByIdCategory(postDTO.getIdCategory()).orElse(null);
		User user = userRepository.findById(postDTO.getIdUser()).orElse(null);

		post.setPostTitle(postDTO.getPostTitle());
		post.setPostSlug(postDTO.getPostSlug());
		post.setPostExcerpt(postDTO.getPostExcerpt());
		post.setPostBody(postDTO.getPostBody());
		post.setTagList(postDTO.getTagList());

		post.setIdCategory(category);
		post.setIdUser(user);

		return postRepository.save(post);
	}

	@Override
	public Post update(PostDTO postDTO) throws PostValidationException {
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

		post.setPostTitle(postDTO.getPostTitle());
		post.setPostSlug(postDTO.getPostSlug());
		post.setPostExcerpt(postDTO.getPostExcerpt());
		post.setPostBody(postDTO.getPostBody());
		post.setTagList(postDTO.getTagList());

		post.setIdCategory(category);

		return postRepository.save(post);
	}

	@Override
	public void delete(Post post) {
		postRepository.delete(post);
	}

	@Override
	public void deleteById(Long idPost) {
		postRepository.deleteById(idPost);
	}

	@Override
	public void deleteAllByIdUser(User idUser) {
		postRepository.deleteAllByIdUser(idUser);
	}

	@Override
	public void deleteAllByIdCategory(Category idCategory) {
		postRepository.deleteAllByIdCategory(idCategory);
	}

	@Override
	public void deleteAllByPostPublished(Boolean postPublished) {
		postRepository.deleteAllByPostPublished(postPublished);
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
