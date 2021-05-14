package rs.digitize.backend.service.impl;

import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.entity.Tag;
import rs.digitize.backend.repository.TagRepository;
import rs.digitize.backend.service.TagService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TagServiceImpl implements TagService {
	private final TagRepository tagRepository;

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag findById(Integer tagId) {
		return tagRepository.findById(tagId)
				.orElseThrow(() -> new NoSuchElementException("TagService.notFound"));
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag update(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void deleteById(Integer tagId) {
		tagRepository.deleteById(tagId);
	}

	@Override
	public List<PostPreview> findAllPostsById(Integer tagId) {
		return findById(tagId).getPosts();
	}
}