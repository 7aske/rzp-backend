
package com.example.backend.controller;

import com.example.backend.adapter.CommentAdapter;
import com.example.backend.entity.Comment;
import com.example.backend.entity.dto.CommentDTO;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.CommentService;
import com.example.backend.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/getById/{idComment}")
	public ResponseEntity<CommentDTO> getById(@PathVariable Long idComment) {
		return ResponseEntity.ok(commentService.findById(idComment));
	}

	@GetMapping("/getAllByIdPost/{idPost}")
	public ResponseEntity<List<CommentDTO>> getByIdPost(@PathVariable Long idPost) {
		return ResponseEntity.ok(commentService.findAllByIdPostIdPost(idPost));
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody CommentDTO comment) {
		try {
			return ResponseEntity.ok(CommentAdapter.adapt(commentService.save(comment)));
		} catch (CommentServiceImpl.CommentValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Comment comment) {
		try {
			commentService.delete(comment);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idComment}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idComment) {
		try {
			commentService.deleteById(idComment);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
