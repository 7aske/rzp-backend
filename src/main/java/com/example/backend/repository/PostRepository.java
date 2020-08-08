package com.example.backend.repository;

import com.example.backend.entity.Category;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByIdPost(Long idPost);

	@Query("select p from Post p left join PostTranslation pt on pt.idPost.idPost = p.idPost left join Language l on l.idLanguage = pt.language.idLanguage where l.languageName = :languageName and pt.postSlugTranslation = :postSlug")
	Optional<Post> findByPostSlug(String postSlug, String languageName);

	@Query("select p from Post p left join PostTranslation pt on pt.idPost.idPost = p.idPost left join Language l where l.languageName = :languageName and pt.postTitleTranslation = :postTitle")
	Optional<Post> findByPostTitle(String postTitle, String languageName);

	List<Post> findAllByIdUser(User idUser);
	List<Post> findAllByIdCategory(Category idCategory);
	List<Post> findAllByPostDatePosted(LocalDate postDatePosted);
	List<Post> findAllByPostDeleted(Boolean postDeleted);
	List<Post> findAllByPostPublished(Boolean postPublished);
	boolean deleteAllByIdUser(User idUser);
	boolean deleteAllByIdCategory(Category idCategory);
	boolean deleteAllByPostDeleted(Boolean postDeleted);
	boolean deleteAllByPostPublished(Boolean postPublished);
}
