
package com.example.backend.controller.author;

import com.example.backend.entity.dto.PostDTO;
import com.example.backend.entity.dto.PostPreviewDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.security.JWTUtils;
import com.example.backend.service.UserPostService;
import com.example.backend.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/author/post")
public class AuthorPostController {
	@Autowired
	private UserPostService postService;

	@GetMapping("/getAll")
	public ResponseEntity<List<PostDTO>> getAll(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer count,
			HttpServletRequest request) {
		Long idUser = null;
		try {
			idUser = JWTUtils.getIdUserFromRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(postService.findAll(idUser, category, tag, page, count, published));
	}

	@GetMapping("/getPageCount")
	public ResponseEntity<Integer> getPageCount(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer count,
			HttpServletRequest request) {
		Long idUser = null;
		try {
			idUser = JWTUtils.getIdUserFromRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(postService.getPageCount(idUser, category, tag, published, count));
	}


	@GetMapping("/getAllPreview")
	public ResponseEntity<List<PostPreviewDTO>> getAllPreviews(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean published,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer count,
			HttpServletRequest request) {
		Long idUser = null;
		try {
			idUser = JWTUtils.getIdUserFromRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(idUser);
		return ResponseEntity.ok(postService.findAllPreviews(idUser, category, tag, page, count, published));
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody PostDTO post, HttpServletRequest request) {
		try {
			Long idUser = JWTUtils.getIdUserFromRequest(request);
			return ResponseEntity.ok(postService.save(idUser, post));
		} catch (PostServiceImpl.PostValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(new ClientError(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody PostDTO post, HttpServletRequest request) {
		try {
			Long idUser = JWTUtils.getIdUserFromRequest(request);
			return ResponseEntity.ok(postService.update(idUser, post));
		} catch (PostServiceImpl.PostValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idPost}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idPost, HttpServletRequest request) {
		try {
			Long idUser = JWTUtils.getIdUserFromRequest(request);
			postService.deleteById(idUser, idPost);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
