
package com.example.backend.controller;

import com.example.backend.entity.PostTag;
import com.example.backend.entity.dto.PostDTO;
import com.example.backend.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/postTag")
public class PostTagController {
	@Autowired
	private PostTagService postTagService;

	@GetMapping("/getAll")
	public ResponseEntity<List<PostTag>> getAll() {
		return ResponseEntity.ok(postTagService.findAll());
	}

	@GetMapping("/getById/{idTag}/{idPost}")
	public ResponseEntity<PostTag> getById(@PathVariable Long idTag, @PathVariable Long idPost) {
		return ResponseEntity.ok(postTagService.findByIdTagAndIdPost(idTag, idPost));
	}

	@PostMapping("/save")
	public ResponseEntity<PostTag> save(@RequestBody PostTag postTag) {
		return ResponseEntity.ok(postTagService.save(postTag));
	}

	@PutMapping("/update")
	public ResponseEntity<PostTag> update(@RequestBody PostTag postTag) {
		return ResponseEntity.ok(postTagService.update(postTag));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody PostTag postTag) {
		return ResponseEntity.ok(postTagService.delete(postTag));
	}

	@DeleteMapping("/deleteById/{idTag}/{idPost}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idTag, @PathVariable Long idPost) {
		return ResponseEntity.ok(postTagService.deleteByIdTagAndIdPost(idTag, idPost));
	}
}
