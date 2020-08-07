
package com.example.backend.controller;

import com.example.backend.entity.Media;
import com.example.backend.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {
	@Autowired
	private MediaService mediaService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Media>> getAll() {
		return ResponseEntity.ok(mediaService.findAll());
	}

	@GetMapping("/getById/{idMedia}")
	public ResponseEntity<Media> getById(@PathVariable Long idMedia) {
		return ResponseEntity.ok(mediaService.findById(idMedia));
	}


	@PostMapping("/save")
	public ResponseEntity<Media> save(@RequestBody Media media) {
		return ResponseEntity.ok(mediaService.save(media));
	}

	@PutMapping("/update")
	public ResponseEntity<Media> update(@RequestBody Media media) {
		return ResponseEntity.ok(mediaService.update(media));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Media media) {
		return ResponseEntity.ok(mediaService.delete(media));
	}

	@DeleteMapping("/deleteById/{idMedia}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idMedia) {
		return ResponseEntity.ok(mediaService.deleteById(idMedia));
	}
}
