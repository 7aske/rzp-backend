package rs.digitize.backend.service;

import rs.digitize.backend.entity.Media;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

	List<Media> findAll();

	Media save(Media media);

	Media update(Media media);

	Media findById(Integer mediaId);

	void deleteById(Integer mediaId);

	Media upload(MultipartFile file);
}