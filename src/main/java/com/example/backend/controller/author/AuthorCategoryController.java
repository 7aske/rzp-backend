
package com.example.backend.controller.author;

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
@RequestMapping("/author/category")
public class AuthorCategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody Category category) {
		try {
			return ResponseEntity.ok(categoryService.save(category));
		} catch (CategoryServiceImpl.CategoryValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody Category category) {
		try {
			return ResponseEntity.ok(categoryService.update(category));
		} catch (CategoryServiceImpl.CategoryValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idCategory}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idCategory) {
		try {
			categoryService.deleteById(idCategory);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
