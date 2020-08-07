package com.example.backend.service;

import com.example.backend.entity.Tag;
import java.util.List;

public interface TagService {

	List<Tag> findAll();

	boolean delete(Tag tag);

	Tag save(Tag tag);

	Tag update(Tag tag);

	Tag findById(Long idTag);

	Tag findAllByTagName(String tagName);

	boolean deleteById(Long idTag);

	boolean deleteAllByTagName(String tagName);
}
