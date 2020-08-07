package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Media;
import com.example.backend.repository.MediaRepository;
import com.example.backend.service.MediaService;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaRepository mediaRepository;

	@Override
	public List<Media> findAll() {
		return mediaRepository.findAll();
	}

	@Override
	public Media findById(Long idMedia) {
		if (mediaRepository.findById(idMedia).isPresent()) {
			return mediaRepository.findById(idMedia).get();
		} else {
			return null;
		}
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
	public boolean delete(Media media) {
		mediaRepository.delete(media);
		return !mediaRepository.findById(media.getIdMedia()).isPresent();
	}

	@Override
	public boolean deleteById(Long idMedia) {
		mediaRepository.deleteById(idMedia);
		return !mediaRepository.findById(idMedia).isPresent();
	}

}
