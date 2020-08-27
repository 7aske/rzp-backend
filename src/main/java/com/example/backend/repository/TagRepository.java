package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.example.backend.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	Optional<Tag> findByIdTag(Long idTag);
	Optional<Tag> findByTagName(String tagName);
	void deleteByTagName(String tagName);
}
