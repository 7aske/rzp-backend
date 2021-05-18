package rs.digitize.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.entity.domain.RecordStatus;
import rs.digitize.backend.repository.PostPreviewRepository;
import rs.digitize.backend.service.PostPreviewService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostPreviewServiceImpl implements PostPreviewService {
	private final PostPreviewRepository postPreviewRepository;

	@Override
	public List<PostPreview> findAll(Pageable pageable) {
		if (pageable == null)
			return findAll();
		return postPreviewRepository.findAllByPublishedTrueAndRecordStatus(pageable, RecordStatus.ACTIVE);
	}

	@Override
	public List<PostPreview> findAll(Specification<PostPreview> specification, Sort sort, Pageable pageable) {
		if (pageable != null)
			return postPreviewRepository.findAll(specification, pageable).toList();
		return postPreviewRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public List<PostPreview> findAll() {
		return postPreviewRepository.findAllByPublishedTrueAndRecordStatus(RecordStatus.ACTIVE);
	}

	@Override
	public PostPreview setRecordStatus(Integer postId, RecordStatus recordStatus) {
		PostPreview postPreview = postPreviewRepository.findById(postId)
				.orElseThrow(() -> new NoSuchElementException("PostPreview.notFound"));
		postPreview.setRecordStatus(recordStatus);
		return postPreviewRepository.save(postPreview);
	}
}
