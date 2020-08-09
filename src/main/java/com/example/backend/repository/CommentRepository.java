package com.example.backend.repository;

import com.example.backend.entity.Post;
import com.example.backend.entity.User;
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
	void deleteAllByIdUser(User idUser);
	void deleteAllByIdPost(Post idPost);
}
