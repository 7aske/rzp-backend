package com.example.backend.repository;

import com.example.backend.entity.PostTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTranslationRepository extends JpaRepository<PostTranslation, Long> {
	boolean deleteAllByIdPostIdPost(Long idPost);

}
