package com.example.backend.repository;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.PostMedia;

@Repository
public interface PostMediaRepository extends JpaRepository<PostMedia, Long> {
	Optional<PostMedia> findByIdMediaAndIdPost(Long idMedia, Long idPost);
	List<PostMedia> findAllByIdMedia(Long idMedia);
	List<PostMedia> findAllByIdPost(Long idPost);
	boolean deleteByIdMediaAndIdPost(Long idMedia, Long idPost);
	boolean deleteAllByIdMedia(Long idMedia);
	boolean deleteAllByIdPost(Long idPost);
}
