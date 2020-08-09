package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Tag;
import com.example.backend.repository.TagRepository;
import com.example.backend.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
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
	public boolean delete(Tag tag) throws Exception {
		tagRepository.delete(tag);
		return !tagRepository.findById(tag.getIdTag()).isPresent();
	}

	@Override
	public boolean deleteById(Long idTag) throws Exception {
		tagRepository.deleteById(idTag);
		return !tagRepository.findById(idTag).isPresent();
	}

	@Override
	public boolean deleteAllByTagName(String tagName) throws Exception {
		return tagRepository.deleteByTagName(tagName);
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
