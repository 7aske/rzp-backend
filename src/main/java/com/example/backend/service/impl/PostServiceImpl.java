package com.example.backend.service.impl;

import com.example.backend.entity.Category;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Post;
import com.example.backend.repository.PostRepository;
import com.example.backend.service.PostService;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

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
	public Post findByPostSlug(String postSlug) {
		return postRepository.findByPostSlug(postSlug).orElse(null);
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
	public List<Post> findAllByPostTitle(String postTitle) {
		return postRepository.findAllByPostTitle(postTitle);
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
	public Post save(Post post) {
		return postRepository.save(post);
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

}
