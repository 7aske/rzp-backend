package com.example.backend.service;

import com.example.backend.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {

	List<Media> findAll();

	void delete(Media media) throws Exception;

	Media save(Media media);

	Media upload(MultipartFile file) throws IOException;

	Media update(Media media);

	Media findById(Long idMedia);

	void deleteById(Long idMedia) throws Exception;
}
