package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	Optional<Media> findByIdMedia(Long idMedia);
	boolean deleteByIdMedia(Long idMedia);
}
