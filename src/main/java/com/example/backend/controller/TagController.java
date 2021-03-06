
package com.example.backend.controller;

import com.example.backend.entity.Tag;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.TagService;
import com.example.backend.service.impl.PostServiceImpl;
import com.example.backend.service.impl.TagServiceImpl;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
	@Autowired
	private TagService tagService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Tag>> getAll() {
		return ResponseEntity.ok(tagService.findAll());
	}

	@GetMapping("/getById/{idTag}")
	public ResponseEntity<Tag> getById(@PathVariable Long idTag) {
		return ResponseEntity.ok(tagService.findById(idTag));
	}

	@GetMapping("/getByName/{tagName}")
	public ResponseEntity<Tag> getById(@PathVariable String tagName) {
		return ResponseEntity.ok(tagService.findAllByTagName(tagName));
	}
}
