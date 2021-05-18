package rs.digitize.backend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.entity.domain.RecordStatus;

import java.util.List;

public interface PostPreviewService {
	List<PostPreview> findAll(Pageable pageable);

	List<PostPreview> findAll(Specification<PostPreview> specification, Sort sort, Pageable pageable);

	List<PostPreview> findAll();

	PostPreview setRecordStatus(Integer postId, RecordStatus recordStatus);
}