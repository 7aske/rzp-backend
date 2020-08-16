package com.example.backend.repository;

import com.example.backend.entity.Category;
import com.example.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.backend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByIdPost(Long idPost);

	Optional<Post> findByPostSlug(String postSlug);

	@Query("select count(p) from Post p")
	Integer countPosts();

	@Query("select count(p) from Post p where p.postPublished = :published")
	Integer countPostsPublished(Boolean published);

	@Query("select count(p) from Post p where p.idCategory.categoryName = :categoryName and p.postPublished = :published order by p.postDatePosted desc")
	Integer countCategoryPostsPublished(String categoryName, Boolean published);

	@Query("select count(p) from Post p where p.idUser.idUser = :idUser and p.postPublished = :published order by p.postDatePosted desc")
	Integer countUserPostsPublished(Long idUser, Boolean published);

	@Query("select count(p) from Post p where p.idUser.idUser = :idUser and p.idCategory.categoryName = :categoryName and p.postPublished = :published order by p.postDatePosted desc")
	Integer countUserCategoryPostsPublished(Long idUser, String categoryName, Boolean published);

	@Query("select count(p) from Post p where p.idCategory.categoryName = :categoryName order by p.postDatePosted desc")
	Integer countCategoryPosts(String categoryName);

	@Query("select count(p) from Post p where p.idUser.idUser = :idUser order by p.postDatePosted desc")
	Integer countUserPosts(Long idUser);

	@Query("select count(p) from Post p where p.idUser.idUser = :idUser and p.idCategory.categoryName = :categoryName order by p.postDatePosted desc")
	Integer countUserCategoryPosts(Long idUser, String categoryName);

	Page<Post> findAllByOrderByPostDatePostedDesc(Pageable page);

	Page<Post> findAllByIdUserOrderByPostDatePostedDesc(User idUser, Pageable pageable);

	Page<Post> findAllByIdCategoryCategoryNameAndPostPublishedOrderByPostDatePostedDesc(String categoryName, Boolean published, Pageable pageable);

	Page<Post> findAllByPostPublishedOrderByPostDatePostedDesc(Boolean postPublished, Pageable pageable);

	Page<Post> findAllByIdCategoryCategoryNameOrderByPostDatePostedDesc(String categoryName, Pageable pageable);

	void deleteAllByIdUser(User idUser);

	void deleteAllByIdCategory(Category idCategory);

	void deleteAllByPostPublished(Boolean postPublished);
}
