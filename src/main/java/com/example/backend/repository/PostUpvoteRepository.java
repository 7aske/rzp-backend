package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.PostUpvote;

@Repository
public interface PostUpvoteRepository extends JpaRepository<PostUpvote, Long> {

}
