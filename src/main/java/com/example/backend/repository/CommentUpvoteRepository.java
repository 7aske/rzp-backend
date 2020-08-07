package com.example.backend.repository;

import com.example.backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.CommentUpvote;

@Repository
public interface CommentUpvoteRepository extends JpaRepository<CommentUpvote, Long> {
	List<CommentUpvote> findAllByIdComment(Comment idComment);
	boolean deleteAllByIdComment(Comment idComment);
}
