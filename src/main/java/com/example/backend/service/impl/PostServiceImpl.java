package com.example.backend.service.impl;

import com.example.backend.adapter.PostAdapter;
import com.example.backend.adapter.PostTranslationAdapter;
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
	public Post findByPostSlug(String postSlug, String locale) {
		return postRepository.findByPostSlug(postSlug, locale).orElse(null);
	}

	@Override
	public PostDTO findDTOByPostSlug(String postSlug, String locale) {
		return PostAdapter.adapt(postRepository.findByPostSlug(postSlug, locale).orElse(null));
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
	public Post findAllByPostTitle(String postTitle, String locale) {
		return postRepository.findByPostTitle(postTitle, locale).orElse(null);
	}

	@Override
	public List<Post> findAllByPostDeleted(Boolean postDeleted) {
		return postRepository.findAllByPostDeleted(postDeleted);
	}

	@Override
	public List<Post> findAllByPostPublished(Boolean postPublished) {
		return postRepository.findAllByPostPublished(postPublished);
	}

	@Override
	public List<PostDTO> findAllDTOByPostPublishedAndLocale(Boolean postPublished, String locale) {
		return postRepository.findAllByPostPublished(postPublished)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public Post save(PostDTO post) throws PostValidationException {
		validatePost(post);

		Post newPost = new Post();
		Category category = categoryRepository.findByIdCategory(post.getIdCategory()).orElse(null);

		newPost.setIdCategory(category);
		newPost.setIdUser(userRepository.findById(post.getIdUser()).orElse(null));

		List<PostTranslation> postTranslations = new ArrayList<>();
		for (PostTranslationDTO postTranslation : post.getPostTranslations()) {
			Language language = languageRepository.findByLanguageName(postTranslation.getLocale()).orElse(null);
			PostTranslation newPostTranslation = PostTranslationAdapter.adapt(postTranslation);
			newPostTranslation.setLanguage(language);
			newPostTranslation.setIdPost(newPost);
			postTranslations.add(newPostTranslation);
		}

		newPost.setPostTranslations(postTranslations);

		return postRepository.save(newPost);
	}

	@Override
	public Post update(PostDTO post) throws PostValidationException {
		validatePost(post);


		Post newPost = postRepository.findByIdPost(post.getIdPost())
				.orElseThrow(() -> new PostValidationException("post.save.post-not-found"));

		Category category = categoryRepository.findByIdCategory(post.getIdCategory()).orElse(null);

		newPost.setIdCategory(category);
		newPost.setPostDeleted(post.getPostDeleted());
		newPost.setPostPublished(post.getPostPublished());

		List<PostTranslation> postTranslations = new ArrayList<>();
		for (PostTranslationDTO postTranslation : post.getPostTranslations()) {
			boolean isSlugValid = newPost.getPostTranslation(postTranslation.getLocale()).getPostSlugTranslation().equals(postTranslation.getPostSlugTranslation()) ||
					!postRepository.findByPostSlug(postTranslation.getPostSlugTranslation(), postTranslation.getLocale()).isPresent();

			if (!isSlugValid) {
				throw new PostValidationException("post.save.slug-exists");
			}

			Language language = languageRepository.findByLanguageName(postTranslation.getLocale()).orElse(null);
			PostTranslation newPostTranslation = PostTranslationAdapter.adapt(postTranslation);
			newPostTranslation.setLanguage(language);
			newPostTranslation.setIdPost(newPost);
			postTranslations.add(newPostTranslation);
		}

		newPost.setPostTranslations(postTranslations);

		return postRepository.save(newPost);
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
	public void deleteAllByPostDeleted(Boolean postDeleted) {
		postRepository.deleteAllByPostDeleted(postDeleted);
	}

	@Override
	public void deleteAllByPostPublished(Boolean postPublished) {
		postRepository.deleteAllByPostPublished(postPublished);
	}

	private void validatePost(PostDTO post) throws PostValidationException {

		if (post.getPostTranslations() == null || post.getPostTranslations().size() == 0) {
			throw new PostValidationException("post.save.translations-invalid");
		}

		for (PostTranslationDTO postTranslation : post.getPostTranslations()) {
			if (postTranslation.getLocale() == null || postTranslation.getLocale().isEmpty() || !Locale.VALID_LOCALES.contains(postTranslation.getLocale())) {
				throw new PostValidationException("post.save.locale-invalid");
			}

			if (postTranslation.getPostSlugTranslation() == null || postTranslation.getPostSlugTranslation().isEmpty()) {
				throw new PostValidationException("post.save.slug-empty");
			}

			if (postTranslation.getPostTitleTranslation() == null || postTranslation.getPostTitleTranslation().isEmpty()) {
				throw new PostValidationException("post.save.title-empty");
			}

			if (postTranslation.getPostBodyTranslation() == null || postTranslation.getPostBodyTranslation().isEmpty()) {
				throw new PostValidationException("post.save.body-empty");
			}

			if (postTranslation.getPostExcerptTranslation() == null || postTranslation.getPostExcerptTranslation().isEmpty()) {
				throw new PostValidationException("post.save.excerpt-empty");
			}
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
