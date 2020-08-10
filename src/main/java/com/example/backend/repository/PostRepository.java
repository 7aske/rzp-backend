package com.example.backend.repository;

import com.example.backend.entity.Category;
import com.example.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByIdPost(Long idPost);
	Optional<Post> findByPostSlug(String postSlug);
	Page<Post> findAllByOrderByPostDatePostedDesc(Pageable page);
	Page<Post> findAllByIdUserOrderByPostDatePostedDesc(User idUser, Pageable pageable);
	Page<Post> findAllByIdCategoryCategoryNameAndPostPublishedOrderByPostDatePostedDesc(String categoryName, Boolean published, Pageable pageable);
	Page<Post> findAllByPostPublishedOrderByPostDatePostedDesc(Boolean postPublished, Pageable pageable);
	Page<Post> findAllByIdCategoryCategoryNameOrderByPostDatePostedDesc(String categoryName, Pageable pageable);
	void deleteAllByIdUser(User idUser);
	void deleteAllByIdCategory(Category idCategory);
	void deleteAllByPostPublished(Boolean postPublished);
}
