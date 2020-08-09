package com.example.backend.repository;

import com.example.backend.entity.Category;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.example.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByIdPost(Long idPost);

	Optional<Post> findByPostSlug(String postSlug);

	Optional<Post> findByPostTitle(String postTitle);

	List<Post> findAllByIdUser(User idUser);

	List<Post> findAllByIdCategory(Category idCategory);

	List<Post> findAllByIdCategoryCategoryName(String categoryName);

	List<Post> findAllByPostPublishedTrueAndIdCategoryCategoryName(String categoryName);

	List<Post> findAllByPostPublished(Boolean postPublished);

	void deleteAllByIdUser(User idUser);

	void deleteAllByIdCategory(Category idCategory);

	void deleteAllByPostPublished(Boolean postPublished);
}
