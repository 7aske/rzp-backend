package com.example.backend.service.impl;

import com.example.backend.entity.Category;
import com.example.backend.entity.PostTranslation;
import com.example.backend.entity.User;
import com.example.backend.entity.data.Locale;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostSaveDTO;
import com.example.backend.entity.dto.PostTranslationSaveDTO;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Post;
import com.example.backend.service.PostService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostTranslationRepository postTranslationRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Post findById(Long idPost) {
		if (postRepository.findById(idPost).isPresent()) {
			return postRepository.findById(idPost).get();
		} else {
			return null;
		}
	}

	@Override
	public Post findByPostSlug(String postSlug, String locale) {
		return postRepository.findByPostSlug(postSlug, locale).orElse(null);
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
				.map(p -> new PostDTO(p, locale))
				.collect(Collectors.toList());
	}

	@Override
	public Post save(PostSaveDTO post) throws PostValidationException {
		if (post.getPostTranslations() == null || post.getPostTranslations().size() == 0) {
			throw new PostValidationException("post.save.translations-invalid");
		}

		for (PostTranslationSaveDTO postTranslation : post.getPostTranslations()) {
			if (postTranslation.getLocale() == null || postTranslation.getLocale().isEmpty() || !Locale.VALID_LOCALES.contains(postTranslation.getLocale())) {
				throw new PostValidationException("post.save.locale-invalid");
			}

			if (postTranslation.getPostSlugTranslation() == null || postTranslation.getPostSlugTranslation().isEmpty()) {
				throw new PostValidationException("post.save.slug-empty");
			}

			if (postRepository.findByPostSlug(postTranslation.getPostSlugTranslation(), postTranslation.getLocale()).isPresent()) {
				throw new PostValidationException("post.save.slug-exists");
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

		Post newPost = new Post();
		newPost.setIdCategory(post.getIdCategory());
		newPost.setIdUser(userRepository.findById(post.getIdUser()).orElse(null));
		newPost.setPostDatePosted(LocalDate.now());
		newPost.setPostViews(0L);
		newPost.setPostPublished(true);
		newPost.setPostDeleted(false);

		List<PostTranslation> postTranslations = new ArrayList<>();
		for (PostTranslationSaveDTO postTranslation : post.getPostTranslations()) {
			PostTranslation newPostTranslation = new PostTranslation();
			newPostTranslation.setLanguage(languageRepository.findByLanguageName(postTranslation.getLocale()).orElse(null));
			newPostTranslation.setPostBodyTranslation(postTranslation.getPostBodyTranslation());
			newPostTranslation.setPostExcerptTranslation(postTranslation.getPostExcerptTranslation());
			newPostTranslation.setPostTitleTranslation(postTranslation.getPostTitleTranslation());
			newPostTranslation.setPostSlugTranslation(postTranslation.getPostSlugTranslation());
			newPostTranslation.setIdPost(newPost);
			postTranslations.add(newPostTranslation);
		}

		newPost.setPostTranslations(postTranslations);

		return postRepository.save(newPost);
	}

	@Override
	public Post update(Post post) {
		return postRepository.save(post);
	}

	@Override
	public boolean delete(Post post) {
		postRepository.delete(post);
		return !postRepository.findById(post.getIdPost()).isPresent();
	}

	@Override
	public boolean deleteById(Long idPost) {
		postRepository.deleteById(idPost);
		return !postRepository.findById(idPost).isPresent();
	}

	@Override
	public boolean deleteAllByIdUser(User idUser) {
		return postRepository.deleteAllByIdUser(idUser);
	}

	@Override
	public boolean deleteAllByIdCategory(Category idCategory) {
		return postRepository.deleteAllByIdCategory(idCategory);
	}

	@Override
	public boolean deleteAllByPostDeleted(Boolean postDeleted) {
		return postRepository.deleteAllByPostDeleted(postDeleted);
	}

	@Override
	public boolean deleteAllByPostPublished(Boolean postPublished) {
		return postRepository.deleteAllByPostPublished(postPublished);
	}

	public static class PostValidationException extends Exception {
		public PostValidationException(String message) {
			super(message);
		}
	}
}
