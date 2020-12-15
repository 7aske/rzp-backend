package com.example.backend.service.impl;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;
import com.example.backend.repository.PostRepository;
import com.example.backend.service.PostService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
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
	public Post findById(Integer postId) {
		return postRepository.findById(postId)
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
	public List<Media> findAllMediasById(Integer postId) {
		return findById(postId).getMedias();
	}

	@Override
	public List<Tag> findAllTagsById(Integer postId) {
		return findById(postId).getTags();
	}


}