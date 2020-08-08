
package com.example.backend.controller;

import com.example.backend.entity.Post;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostSaveDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.service.PostService;
import com.example.backend.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Post>> getAll() {
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/getById/{idPost}")
	public ResponseEntity<Post> getById(@PathVariable Long idPost) {
		return ResponseEntity.ok(postService.findById(idPost));
	}

	@GetMapping("/getAllPublished")
	public ResponseEntity<List<PostDTO>> getAllPublished(HttpServletRequest request) {
		String locale = (String) request.getAttribute("locale");
		return ResponseEntity.ok(postService.findAllDTOByPostPublishedAndLocale(true, locale));

	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody PostSaveDTO post) {
		try {
			return ResponseEntity.ok(new PostDTO(postService.save(post)));
		} catch (PostServiceImpl.PostValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Post> update(@RequestBody Post post) {
		return ResponseEntity.ok(postService.update(post));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Post post) {
		return ResponseEntity.ok(postService.delete(post));
	}

	@DeleteMapping("/deleteById/{idPost}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idPost) {
		return ResponseEntity.ok(postService.deleteById(idPost));
	}
}
