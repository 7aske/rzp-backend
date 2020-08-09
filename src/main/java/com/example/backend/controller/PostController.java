
package com.example.backend.controller;

import com.example.backend.entity.Post;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
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
		Post post = postService.findById(idPost);
		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getAllPublished")
	public ResponseEntity<List<PostDTO>> getAllPublished(HttpServletRequest request) {
		String locale = (String) request.getAttribute("locale");
		return ResponseEntity.ok(postService.findAllDTOByPostPublishedAndLocale(true, locale));
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody PostDTO post) {
		try {
			return ResponseEntity.ok(postService.save(post));
		} catch (PostServiceImpl.PostValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody PostDTO post) {
		try {
			return ResponseEntity.ok(postService.update(post));
		} catch (PostServiceImpl.PostValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Post post) {
		try {
			postService.delete(post);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idPost}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idPost) {
		try {
			postService.deleteById(idPost);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
