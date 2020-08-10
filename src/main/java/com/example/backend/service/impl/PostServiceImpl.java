package com.example.backend.service.impl;

import com.example.backend.adapter.PostAdapter;
import com.example.backend.entity.*;
import com.example.backend.entity.dto.*;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.service.PostService;

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
	public List<PostDTO> findAll() {
		return postRepository.findAll()
				.stream()
				.map(PostAdapter::adapt)
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
	public List<PostDTO> findAllByIdUser(User idUser) {
		return postRepository.findAllByIdUserOrderByPostDatePostedDesc(idUser)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAllByIdCategory(Category idCategory) {
		return postRepository.findAllByPostPublishedTrueAndIdCategoryOrderByPostDatePostedDesc(idCategory)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAllByIdCategoryCategoryName(String categoryName) {
		return postRepository.findAllByPostPublishedTrueAndIdCategoryCategoryNameOrderByPostDatePostedDesc(categoryName)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAllByPostPublished(Boolean postPublished) {
		return postRepository.findAllByPostPublishedOrderByPostDatePostedDesc(postPublished)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAllByPostPublishedTrue() {
		return postRepository.findAllByPostPublishedTrueOrderByPostDatePostedDesc()
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAllDTOByPostPublishedTrueAndCategoryName(String categoryName) {
		return postRepository.findAllByPostPublishedTrueAndIdCategoryCategoryNameOrderByPostDatePostedDesc(categoryName)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public PostDTO save(PostDTO postDTO) throws PostValidationException {
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

		post.setPostTitle(postDTO.getPostTitle());
		post.setPostSlug(postDTO.getPostSlug());
		post.setPostExcerpt(postDTO.getPostExcerpt());
		post.setPostBody(postDTO.getPostBody());
		post.setTagList(postDTO.getTagList());

		post.setIdCategory(category);

		return PostAdapter.adapt(postRepository.save(post));
	}

	@Override
	public void delete(Post post) throws Exception{
		postRepository.delete(post);
	}

	@Override
	public void deleteById(Long idPost) throws Exception {
		postRepository.deleteById(idPost);
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
