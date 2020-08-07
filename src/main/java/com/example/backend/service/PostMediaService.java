package com.example.backend.service;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.entity.PostMedia;
import java.util.List;

public interface PostMediaService {

	List<PostMedia> findAll();

	boolean delete(PostMedia postMedia);

	PostMedia save(PostMedia postMedia);

	PostMedia update(PostMedia postMedia);


	PostMedia findByIdMediaAndIdPost(Long idMedia, Long idPost);

	List<PostMedia> findAllByIdMedia(Long idMedia);

	List<PostMedia> findAllByIdPost(Long idPost);


	boolean deleteByIdMediaAndIdPost(Long idMedia, Long idPost);

	boolean deleteAllByIdMedia(Long idMedia);

	boolean deleteAllByIdPost(Long idPost);
}
