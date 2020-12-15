package com.example.backend.service.impl;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.repository.MediaRepository;
import com.example.backend.service.MediaService;
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
public class MediaServiceImpl implements MediaService {
	private final MediaRepository mediaRepository;

	@Override
	public List<Media> findAll() {
		return mediaRepository.findAll();
	}

	@Override
	public Media findById(Integer mediaId) {
		return mediaRepository.findById(mediaId)
				.orElseThrow(() -> new NoSuchElementException("MediaService.notFound"));
	}

	@Override
	public Media save(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public Media update(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public void deleteById(Integer mediaId) {
		mediaRepository.deleteById(mediaId);
	}

	@Override
	public List<Post> findAllPostsById(Integer mediaId) {
		return findById(mediaId).getPosts();
	}


}