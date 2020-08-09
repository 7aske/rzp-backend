package com.example.backend.service;

import com.example.backend.entity.Tag;
import com.example.backend.service.impl.TagServiceImpl;

import java.util.List;

public interface TagService {

	List<Tag> findAll();

	boolean delete(Tag tag);

	Tag save(Tag tag) throws TagServiceImpl.TagValidationException;

	Tag update(Tag tag) throws TagServiceImpl.TagValidationException;

	Tag findById(Long idTag);

	Tag findAllByTagName(String tagName);

	boolean deleteById(Long idTag);

	boolean deleteAllByTagName(String tagName);
}
