package com.example.backend.repository;

import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.PostTag;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {
	Optional<PostTag> findByIdTagAndIdPost(Long idTag, Long idPost);
	List<PostTag> findAllByIdTag(Long idTag);
	List<PostTag> findAllByIdPost(Long idPost);
	boolean deleteByIdTagAndIdPost(Long idTag, Long idPost);
	boolean deleteAllByIdTag(Long idTag);
	boolean deleteAllByIdPost(Long idPost);
}
