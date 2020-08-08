package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import com.example.backend.entity.data.Locale;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostSaveDTO;
import com.example.backend.service.impl.PostServiceImpl;

import java.time.LocalDate;
import java.util.List;

public interface PostService {

	List<Post> findAll();

	boolean delete(Post post);

	Post save(PostSaveDTO post) throws PostServiceImpl.PostValidationException;

	Post update(Post post);

	Post findById(Long idPost);

	Post findByPostSlug(String postSlug, String locale);

	List<Post> findAllByIdUser(User idUser);

	List<Post> findAllByIdCategory(Category idCategory);

	Post findAllByPostTitle(String postTitle, String locale);

	List<Post> findAllByPostDeleted(Boolean postDeleted);

	List<Post> findAllByPostPublished(Boolean postPublished);

	List<PostDTO> findAllDTOByPostPublishedAndLocale(Boolean postPublished, String locale);

	boolean deleteById(Long idPost);

	boolean deleteAllByIdUser(User idUser);

	boolean deleteAllByIdCategory(Category idCategory);

	boolean deleteAllByPostDeleted(Boolean postDeleted);

	boolean deleteAllByPostPublished(Boolean postPublished);

}
