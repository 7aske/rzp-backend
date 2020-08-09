package com.example.backend.service.impl;

import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.PostTag;
import com.example.backend.repository.PostTagRepository;
import com.example.backend.service.PostTagService;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {

	@Autowired
	private PostTagRepository postTagRepository;

	@Override
	public List<PostTag> findAll() {
		return postTagRepository.findAll();
	}

	@Override
	public boolean delete(PostTag postTag) {
		postTagRepository.delete(postTag);
		return true;
	}

	@Override
	public PostTag save(PostTag postTag) {
		return postTagRepository.save(postTag);
	}

	@Override
	public PostTag update(PostTag postTag) {
		return postTagRepository.save(postTag);
	}

	@Override
	public PostTag findByIdTagAndIdPost(Long idTag, Long idPost) {
		return postTagRepository.findByIdTagAndIdPost(idTag, idPost).orElse(null);
	}

	@Override
	public List<PostTag> findAllByIdTag(Long idTag) {
		return postTagRepository.findAllByIdTag(idTag);
	}

	@Override
	public List<PostTag> findAllByIdPost(Long idPost) {
		return postTagRepository.findAllByIdPost(idPost);
	}

	@Override
	public boolean deleteByIdTagAndIdPost(Long idTag, Long idPost) {
		return postTagRepository.deleteByIdTagAndIdPost(idTag, idPost);
	}

	@Override
	public boolean deleteAllByIdTag(Long idTag) {
		return false;
	}

	@Override
	public boolean deleteAllByIdPost(Long idPost) {
		return false;
	}
}
