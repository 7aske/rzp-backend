package com.example.backend.service;

import com.example.backend.entity.Media;
import java.util.List;

public interface MediaService {

	List<Media> findAll();

	boolean delete(Media media);

	Media save(Media media);

	Media update(Media media);

	Media findById(Long idMedia);

	boolean deleteById(Long idMedia);
}
