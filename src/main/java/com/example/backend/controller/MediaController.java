
package com.example.backend.controller;

import com.example.backend.adapter.MediaAdapter;
import com.example.backend.entity.Media;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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


	@PostMapping("/upload")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {
		try {
			return ResponseEntity.ok(mediaService.upload(file));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@PostMapping("/mdeupload")
	public ResponseEntity<Object> mdeUpload(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
		try {
			return ResponseEntity.ok(MediaAdapter.adapt(mediaService.upload(file)));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Media media) {
		try {
			mediaService.delete(media);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idMedia}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idMedia) {
		try {
			mediaService.deleteById(idMedia);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
