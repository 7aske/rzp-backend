package com.example.backend.controller;

import com.example.backend.entity.Media;
import com.example.backend.entity.Post;
import com.example.backend.entity.Tag;
import com.example.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping
	public ResponseEntity<List<Post>> getAll() {
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/{postId}")
	public ResponseEntity<Post> getById(@PathVariable String postId) {
		try {
			return ResponseEntity.ok(postService.findById(Integer.parseInt(postId)));
		} catch (NumberFormatException ignored) {
			return ResponseEntity.ok(postService.findBySlug(postId));
		}
	}

	@PostMapping
	public ResponseEntity<Post> save(@RequestBody Post post) {
		return ResponseEntity.ok(postService.save(post));
	}

	@PutMapping
	public ResponseEntity<Post> update(@RequestBody Post post) {
		return ResponseEntity.ok(postService.update(post));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<Post> updateById(@RequestBody Post post, @PathVariable Integer postId) {
		post.setId(postId);
		return ResponseEntity.ok(postService.update(post));
	}

	@DeleteMapping("/{postId}")
	public void deleteById(@PathVariable Integer postId) {
		postService.deleteById(postId);
	}

	@GetMapping("/{postId}/tags")
	public ResponseEntity<List<Tag>> getAllTags(@PathVariable Integer postId) {
		return ResponseEntity.ok(postService.findAllTagsById(postId));
	}

}

