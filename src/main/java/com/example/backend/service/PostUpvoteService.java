package com.example.backend.service;

import com.example.backend.entity.Post;
import com.example.backend.entity.PostUpvote;
import java.util.List;

public interface PostUpvoteService {

	List<PostUpvote> findAll();

	boolean delete(PostUpvote postUpvote);

	PostUpvote save(PostUpvote postUpvote);

	PostUpvote update(PostUpvote postUpvote);

	PostUpvote findByPostUpvoteHash(String postUpvoteHash);

	List<PostUpvote> findAllByIdPost(Post idPost);

	PostUpvote deleteByPostUpvoteHash(String postUpvoteHash);

	boolean deleteAllByIdPost(Post idPost);
}
