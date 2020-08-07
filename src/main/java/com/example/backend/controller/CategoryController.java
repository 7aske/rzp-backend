
package com.example.backend.controller;

import com.example.backend.entity.Category;
import com.example.backend.service.CategoryService;
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


	@PostMapping("/save")
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.save(category));
	}

	@PutMapping("/update")
	public ResponseEntity<Category> update(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.update(category));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.delete(category));
	}

	@DeleteMapping("/deleteById/{idCategory}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idCategory) {
		return ResponseEntity.ok(categoryService.deleteById(idCategory));
	}
}
