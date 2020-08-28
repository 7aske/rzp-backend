
package com.example.backend.controller;

import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostPreviewDTO;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("/getAll")
	public ResponseEntity<List<PostDTO>> getAll(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer count) {

		return ResponseEntity.ok(postService.findAll(category, tag, page, count, published));
	}

	@GetMapping("/getPageCount")
	public ResponseEntity<Integer> getPageCount(
			@RequestParam(required = false) Long idUser,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer count) {
		return ResponseEntity.ok(postService.getPageCount(idUser, category, tag, published, count));
	}

	@GetMapping("/getAllPreview")
	public ResponseEntity<List<PostPreviewDTO>> getAllPreviews(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer count) {

		return ResponseEntity.ok(postService.findAllPreviews(category, tag, page, count, published));
	}

	@GetMapping("/getAllByIdUser/{idUser}")
	public ResponseEntity<List<PostDTO>> getAllByIdUser(
			@PathVariable Long idUser,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer count) {

		return ResponseEntity.ok(postService.findAllByIdUser(idUser, category, page, count, published));
	}

	@GetMapping("/getById/{idPost}")
	public ResponseEntity<PostDTO> getById(@PathVariable Long idPost) {
		PostDTO post = postService.findById(idPost);
		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/getByPostSlug/{postSlug}")
	public ResponseEntity<PostDTO> getByPostSlug(@PathVariable String postSlug) {
		PostDTO post = postService.findByPostSlug(postSlug);
		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
