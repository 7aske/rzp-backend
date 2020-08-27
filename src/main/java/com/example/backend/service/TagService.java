package com.example.backend.service;

import com.example.backend.entity.Tag;
import com.example.backend.entity.dto.TagStatsDTO;
import com.example.backend.service.impl.TagServiceImpl;

import java.util.List;

public interface TagService {

	List<Tag> findAll();

	TagStatsDTO getStats();

	void delete(Tag tag) throws Exception;

	Tag save(Tag tag) throws TagServiceImpl.TagValidationException;

	Tag update(Tag tag) throws TagServiceImpl.TagValidationException;

	Tag findById(Long idTag);

	Tag findAllByTagName(String tagName);

	void deleteById(Long idTag) throws Exception;

	void deleteAllByTagName(String tagName) throws Exception;
}
