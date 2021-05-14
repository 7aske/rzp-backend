package rs.digitize.backend.service;

import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.entity.Tag;

import java.util.List;

public interface TagService {

	List<Tag> findAll();

	Tag save(Tag tag);

	Tag update(Tag tag);

	Tag findById(Integer tagId);

	void deleteById(Integer tagId);

	List<PostPreview> findAllPostsById(Integer tagId);

}