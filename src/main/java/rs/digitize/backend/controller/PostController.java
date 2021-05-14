package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.digitize.backend.entity.*;
import rs.digitize.backend.service.*;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllPosts")
	public ResponseEntity<List<Post>> getAllPosts(@RequestParam(name = "q", required = false) Specification<Post> specification,
	                                              @RequestParam(name = "page", required = false) Pageable pageable,
	                                              @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(postService.findAll(specification, sort, pageable));
	}

	@GetMapping("/{postId}")
	@ApiOperation(value = "", nickname = "getPostById")
	public ResponseEntity<Post> getPostById(@PathVariable Integer postId) {
		return ResponseEntity.ok(postService.findById(postId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "savePost")
	public ResponseEntity<Post> savePost(@RequestBody Post post) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updatePost")
	public ResponseEntity<Post> updatePost(@RequestBody Post post) {
		return ResponseEntity.ok(postService.update(post));
	}

	@DeleteMapping("/{postId}")
	@ApiOperation(value = "", nickname = "deletePostById")
	public void deletePostById(@PathVariable Integer postId) {
		postService.deleteById(postId);
	}

	@GetMapping("/{postId}/tags")
	@ApiOperation(value = "", nickname = "getPostTags")
	public ResponseEntity<List<Tag>> getPostTags(@PathVariable Integer postId) {
		return ResponseEntity.ok(postService.findAllTagsById(postId));
	}

	@PostMapping("/{postId}/tags")
	@ApiOperation(value = "", nickname = "setPostTags")
	public ResponseEntity<List<Tag>> setPostTags(@PathVariable Integer postId, @RequestBody List<Tag> tags) {
		return ResponseEntity.ok(postService.setTagsById(postId, tags));
	}

	@PutMapping("/{postId}/tags")
	@ApiOperation(value = "", nickname = "addPostTags")
	public ResponseEntity<List<Tag>> addPostTags(@PathVariable Integer postId, @RequestBody List<Tag> tags) {
		return ResponseEntity.ok(postService.addTagsById(postId, tags));
	}

	@DeleteMapping("/{postId}/tags")
	@ApiOperation(value = "", nickname = "deletePostTags")
	public ResponseEntity<List<Tag>> deletePostTags(@PathVariable Integer postId, @RequestBody List<Tag> tags) {
		return ResponseEntity.ok(postService.deleteTagsById(postId, tags));
	}
}

