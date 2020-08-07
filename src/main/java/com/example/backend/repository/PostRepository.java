package com.example.backend.repository;

import com.example.backend.entity.Category;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByIdPost(Long idPost);
	Optional<Post> findByPostSlug(String postSlug);
	List<Post> findAllByIdUser(User idUser);
	List<Post> findAllByIdCategory(Category idCategory);
	List<Post> findAllByPostTitle(String postTitle);
	List<Post> findAllByPostDatePosted(LocalDate postDatePosted);
	List<Post> findAllByPostDeleted(Boolean postDeleted);
	List<Post> findAllByPostPublished(Boolean postPublished);
	boolean deleteAllByIdUser(User idUser);
	boolean deleteAllByPostTitle(String postTitle);
	boolean deleteAllByIdCategory(Category idCategory);
	boolean deleteAllByPostDeleted(Boolean postDeleted);
	boolean deleteAllByPostPublished(Boolean postPublished);
}
