
package com.example.backend.controller;

import com.example.backend.entity.PostMedia;
import com.example.backend.service.PostMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postMedia")
public class PostMediaController {
	@Autowired
	private PostMediaService postMediaService;

	@GetMapping("/getAll")
	public ResponseEntity<List<PostMedia>> getAll() {
		return ResponseEntity.ok(postMediaService.findAll());
	}

	@GetMapping("/getById/idMedia/idPost")
	public ResponseEntity<PostMedia> getById(@PathVariable Long idMedia, @PathVariable Long idPost) {
		return ResponseEntity.ok(postMediaService.findByIdMediaAndIdPost(idMedia, idPost));
	}


	@PostMapping("/save")
	public ResponseEntity<PostMedia> save(@RequestBody PostMedia postMedia) {
		return ResponseEntity.ok(postMediaService.save(postMedia));
	}

	@PutMapping("/update")
	public ResponseEntity<PostMedia> update(@RequestBody PostMedia postMedia) {
		return ResponseEntity.ok(postMediaService.update(postMedia));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody PostMedia postMedia) {
		return ResponseEntity.ok(postMediaService.delete(postMedia));
	}

	@DeleteMapping("/deleteById/idMedia/idPost")
	public ResponseEntity<Object> deleteById(@PathVariable Long idMedia, @PathVariable Long idPost) {
		return ResponseEntity.ok(postMediaService.deleteByIdMediaAndIdPost(idMedia, idPost));
	}
}
