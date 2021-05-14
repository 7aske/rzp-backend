package rs.digitize.backend.service;

import org.springframework.data.domain.Sort;
import rs.digitize.backend.entity.PostPreview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PostPreviewService {
	List<PostPreview> findAll(Pageable pageable);

	List<PostPreview> findAll(Specification<PostPreview> specification, Sort sort, Pageable pageable);

	List<PostPreview> findAll();
}