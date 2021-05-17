package rs.digitize.backend.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.Post;
import rs.digitize.backend.entity.Tag;

import java.util.List;

public interface TagService {

	List<Tag> findAll(Specification<Tag> specification, Sort sort);

	Tag save(Tag tag);

	Tag update(Tag tag);

	Tag findById(Integer tagId);

	void deleteById(Integer tagId);

	List<Post> findAllPostsById(Integer tagId);

	List<Post> addPostsById(Integer tagId, List<Post> posts);

	List<Post> setPostsById(Integer tagId, List<Post> posts);

	List<Post> deletePostsById(Integer tagId, List<Post> posts);

}