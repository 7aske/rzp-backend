package rs.digitize.backend.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;
import rs.digitize.backend.entity.Media;

import java.util.List;

public interface MediaService {

	List<Media> findAll();

	List<Media> findAll(Specification<Media> specification, Sort sort);

	Media save(Media media);

	Media update(Media media);

	Media findById(Integer mediaId);

	void deleteById(Integer mediaId);

	Media upload(MultipartFile file);
}