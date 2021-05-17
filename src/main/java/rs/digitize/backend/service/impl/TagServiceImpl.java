package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.Post;
import rs.digitize.backend.entity.Tag;
import rs.digitize.backend.repository.TagRepository;
import rs.digitize.backend.service.TagService;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TagServiceImpl implements TagService {
	private final TagRepository tagRepository;

	@Override
	public List<Tag> findAll(Specification<Tag> specification, Sort sort) {
		return tagRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
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
	public List<Post> findAllPostsById(Integer tagId) {
		return findById(tagId).getPosts();
	}

	@Override
	public List<Post> addPostsById(Integer tagId, List<Post> posts) {
		Tag tag = findById(tagId);
		tag.getPosts().addAll(posts);
		return tagRepository.save(tag).getPosts();
	}

	@Override
	public List<Post> setPostsById(Integer tagId, List<Post> posts) {
		Tag tag = findById(tagId);
		tag.setPosts(posts);
		return tagRepository.save(tag).getPosts();
	}

	@Override
	public List<Post> deletePostsById(Integer tagId, List<Post> posts) {
		Tag tag = findById(tagId);
		tag.getPosts().removeAll(posts);
		return tagRepository.save(tag).getPosts();
	}


}