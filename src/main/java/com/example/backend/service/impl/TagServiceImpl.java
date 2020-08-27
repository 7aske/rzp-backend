package com.example.backend.service.impl;

import com.example.backend.entity.dto.TagStatsDTO;
import com.example.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Tag;
import com.example.backend.repository.TagRepository;
import com.example.backend.service.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public TagStatsDTO getStats() {
		TagStatsDTO tagStatsDTO = new TagStatsDTO();
		Map<String, Number> stats = new HashMap<>();
		List<Tag> tags = tagRepository.findAll();
		for (Tag tag : tags) {
			Integer count = postRepository.countByTagName(tag.getTagName());
			stats.put(tag.getTagName(), count);
		}

		tagStatsDTO.setStats(stats);

		return tagStatsDTO;
	}
	@Override
	public Tag findById(Long idTag) {
		return tagRepository.findById(idTag).orElse(null);
	}

	@Override
	public Tag findAllByTagName(String tagName) {
		return tagRepository.findByTagName(tagName).orElse(null);
	}

	@Override
	public Tag save(Tag tag) throws TagValidationException {
		validate(tag);
		if (tagRepository.findByTagName(tag.getTagName()).isPresent()) {
			throw new TagValidationException("tag.save.name-exists");
		}
		return tagRepository.save(tag);
	}

	@Override
	public Tag update(Tag newTag) throws TagValidationException {
		validate(newTag);
		Tag tag = tagRepository.findByIdTag(newTag.getIdTag()).orElseThrow(() -> new TagValidationException("tag.save.tag-not-found"));
		boolean isTagNameValid = tag.getTagName().equals(newTag.getTagName()) ||
				!tagRepository.findByTagName(newTag.getTagName()).isPresent();

		if (!isTagNameValid) {
			throw new TagValidationException("tag.save.name-exists");
		}

		tag.setTagName(newTag.getTagName());

		return tagRepository.save(tag);
	}

	@Override
	public void delete(Tag tag) throws Exception {
		tagRepository.delete(tag);
	}

	@Override
	public void deleteById(Long idTag) throws Exception {
		tagRepository.deleteById(idTag);
	}

	@Override
	public void deleteAllByTagName(String tagName) throws Exception {
		tagRepository.deleteByTagName(tagName);
	}

	private void validate(Tag tag) throws TagValidationException {
		if (tag.getTagName() == null || tag.getTagName().isEmpty()) {
			throw new TagValidationException("tag.save.name-invalid");
		}
	}

	public static class TagValidationException extends Exception {
		public TagValidationException(String message) {
			super(message);
		}
	}
}
