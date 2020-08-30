package com.example.backend.repository;

import com.example.backend.entity.Post;
import com.example.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Optional<Comment> findByIdComment(Long idComment);
	List<Comment> findAllByIdUser(User idUser);
	List<Comment> findAllByIdPost(Post idPost);
	List<Comment> findAllByIdPostIdPost(Long idPost);
	Page<Comment> findAllByIdPostIdPost(Long idPost, Pageable pageable);
	void deleteAllByIdUser(User idUser);
	void deleteAllByIdPost(Post idPost);
}
