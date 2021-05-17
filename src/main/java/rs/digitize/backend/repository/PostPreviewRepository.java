package rs.digitize.backend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.entity.domain.RecordStatus;

import java.util.List;


@Repository
public interface PostPreviewRepository extends JpaRepository<PostPreview, Integer>, JpaSpecificationExecutor<PostPreview> {
	List<PostPreview> findAllByPublishedTrueAndRecordStatus(Pageable pageable, RecordStatus recordStatus);
	List<PostPreview> findAllByPublishedTrueAndRecordStatus(RecordStatus recordStatus);
	List<PostPreview> findAll(Specification<PostPreview> specification);
}