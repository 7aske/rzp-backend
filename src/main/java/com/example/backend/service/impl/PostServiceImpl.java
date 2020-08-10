package com.example.backend.service.impl;

import com.example.backend.adapter.PostAdapter;
import com.example.backend.adapter.PostPreviewDTOAdapter;
import com.example.backend.entity.*;
import com.example.backend.entity.dto.*;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	private CategoryRepository categoryRepository;

	private final Integer PAGE_SIZE = 5;

	@Override
	public List<PostDTO> findAll(String categoryName, Integer pageNumber, Integer pageSize, Boolean published) {
		return getPostsByQuery(categoryName,pageNumber, pageSize, published)
				.stream()
				.map(PostAdapter::adapt)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostPreviewDTO> findAllPreviews(String categoryName, Integer pageNumber, Integer pageSize, Boolean published) {
		return getPostsByQuery(categoryName,pageNumber, pageSize, published)
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
		return findAll(categoryName, pageNumber, pageSize, published)
				.stream()
				.filter(p -> p.getIdUser().equals(idUser))
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

	private Page<Post> getPostsByQuery(String categoryName, Integer pageNumber, Integer pageSize, Boolean published){
		Pageable page = getPage(pageNumber, pageSize);
		if (categoryName != null && published != null) {
			return postRepository.findAllByIdCategoryCategoryNameAndPostPublishedOrderByPostDatePostedDesc(categoryName, published, page);
		} else if (categoryName != null) {
			return postRepository.findAllByIdCategoryCategoryNameOrderByPostDatePostedDesc(categoryName, page);
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
