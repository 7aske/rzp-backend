package com.example.backend.service.impl;

import com.example.backend.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Category;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.service.CategoryService;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long idCategory) {
		return categoryRepository.findByIdCategory(idCategory).orElse(null);
	}

	@Override
	public Category findByCategoryName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName).orElse(null);
	}

	@Override
	public Category save(Category category) throws CategoryValidationException {
		validate(category);
		if (categoryRepository.findByCategoryName(category.getCategoryName()).isPresent()) {
			throw new CategoryValidationException("category.save.name-exists");
		}

		return categoryRepository.save(category);
	}

	@Override
	public Category update(Category newCategory) throws CategoryValidationException {
		validate(newCategory);
		Category category = categoryRepository.findByIdCategory(newCategory.getIdCategory()).orElseThrow(() -> new CategoryValidationException("category.save.category-not-found"));
		boolean isTagNameValid = category.getCategoryName().equals(newCategory.getCategoryName()) ||
				!categoryRepository.findByCategoryName(newCategory.getCategoryName()).isPresent();

		if (!isTagNameValid) {
			throw new CategoryValidationException("category.save.name-exists");
		}

		category.setCategoryName(newCategory.getCategoryName());

		return categoryRepository.save(newCategory);
	}

	@Override
	public void delete(Category category) throws Exception {
		categoryRepository.delete(category);
	}

	@Override
	public void deleteById(Long idCategory) throws Exception {
		categoryRepository.deleteById(idCategory);
	}

	@Override
	public void deleteAllByCategoryName(String categoryName) throws Exception {
		categoryRepository.deleteAllByCategoryName(categoryName);
	}

	private void validate(Category category) throws CategoryValidationException {
		if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
			throw new CategoryValidationException("category.save.name-invalid");
		}
	}

	public static class CategoryValidationException extends Exception {
		public CategoryValidationException(String message) {
			super(message);
		}
	}
}
