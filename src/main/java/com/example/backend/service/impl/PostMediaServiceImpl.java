package com.example.backend.service.impl;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.PostMedia;
import com.example.backend.repository.PostMediaRepository;
import com.example.backend.service.PostMediaService;
import java.util.List;

@Service
public class PostMediaServiceImpl implements PostMediaService {

	@Autowired
	private PostMediaRepository postMediaRepository;

	@Override
	public List<PostMedia> findAll() {
		return postMediaRepository.findAll();
	}

	@Override
	public boolean delete(PostMedia postMedia) {
		return false;
	}

	@Override
	public PostMedia save(PostMedia postMedia) {
		return postMediaRepository.save(postMedia);
	}

	@Override
	public PostMedia update(PostMedia postMedia) {
		return postMediaRepository.save(postMedia);
	}

	@Override
	public PostMedia findByIdMediaAndIdPost(Long idMedia, Long idPost) {
		return postMediaRepository.findByIdMediaAndIdPost(idMedia, idPost).orElse(null);
	}

	@Override
	public List<PostMedia> findAllByIdMedia(Long idMedia) {
		return postMediaRepository.findAllByIdMedia(idMedia);
	}

	@Override
	public List<PostMedia> findAllByIdPost(Long idPost) {
		return postMediaRepository.findAllByIdPost(idPost);
	}

	@Override
	public boolean deleteByIdMediaAndIdPost(Long idMedia, Long idPost) {
		return postMediaRepository.deleteByIdMediaAndIdPost(idMedia, idPost);
	}

	@Override
	public boolean deleteAllByIdMedia(Long idMedia) {
		return postMediaRepository.deleteAllByIdMedia(idMedia);
	}

	@Override
	public boolean deleteAllByIdPost(Long idPost) {
		return postMediaRepository.deleteAllByIdPost(idPost);
	}

}
