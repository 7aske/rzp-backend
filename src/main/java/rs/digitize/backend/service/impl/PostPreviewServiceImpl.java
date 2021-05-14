package rs.digitize.backend.service.impl;

import org.springframework.data.domain.Sort;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.repository.PostPreviewRepository;
import rs.digitize.backend.service.PostPreviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostPreviewServiceImpl implements PostPreviewService {
	private final PostPreviewRepository postPreviewRepository;

	@Override
	public List<PostPreview> findAll(Pageable pageable) {
		if (pageable == null)
			return findAll();
		return postPreviewRepository.findAllByPublishedTrueAndDeletedFalse(pageable);
	}

	@Override
	public List<PostPreview> findAll(Specification<PostPreview> specification, Sort sort, Pageable pageable) {
		if (pageable != null)
			return postPreviewRepository.findAll(specification, pageable).toList();
		return postPreviewRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public List<PostPreview> findAll() {
		return postPreviewRepository.findAllByPublishedTrueAndDeletedFalse();
	}
}
