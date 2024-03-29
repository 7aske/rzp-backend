package rs.digitize.backend.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.data.PostSummary;
import rs.digitize.backend.entity.Post;
import rs.digitize.backend.entity.Tag;

import java.util.List;

public interface PostService {

	List<Post> findAll();

	List<Post> findAll(Specification<Post> specification, Sort sort, Pageable pageable);

	Long count(Specification<Post> query);

	Post save(Post post);

	Post update(Post post);

	Post findById(Integer postId);

	Post findBySlug(String slug);

	void deleteById(Integer postId);

	List<Tag> findAllTagsById(Integer postId);

	List<Tag> addTagsById(Integer postId, List<Tag> tags);

	List<Tag> setTagsById(Integer postId, List<Tag> tags);

	List<Tag> deleteTagsById(Integer postId, List<Tag> tags);

	PostSummary getSummary(PostSummary.SummaryType type);
}