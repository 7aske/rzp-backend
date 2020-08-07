package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface PostService {

	List<Post> findAll();

	boolean delete(Post post);

	Post save(Post post);

	Post update(Post post);

	Post findById(Long idPost);

	Post findByPostSlug(String postSlug);

	List<Post> findAllByIdUser(User idUser);

	List<Post> findAllByIdCategory(Category idCategory);

	List<Post> findAllByPostTitle(String postTitle);

	List<Post> findAllByPostDeleted(Boolean postDeleted);

	List<Post> findAllByPostPublished(Boolean postPublished);

	boolean deleteById(Long idPost);

	boolean deleteAllByIdUser(User idUser);

	boolean deleteAllByIdCategory(Category idCategory);

	boolean deleteAllByPostDeleted(Boolean postDeleted);

	boolean deleteAllByPostPublished(Boolean postPublished);

}
