
package com.example.backend.controller;

import com.example.backend.entity.Category;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.CategoryService;
import com.example.backend.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/getById/{idCategory}")
	public ResponseEntity<Category> getById(@PathVariable Long idCategory) {
		return ResponseEntity.ok(categoryService.findById(idCategory));
	}
}
