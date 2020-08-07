package com.example.backend.service.impl;

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
		if (categoryRepository.findById(idCategory).isPresent()) {
			return categoryRepository.findById(idCategory).get();
		} else {
			return null;
		}
	}

	@Override
	public List<Category> findAllByCategoryName(String categoryName) {
		return categoryRepository.findAllByCategoryName(categoryName);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public boolean delete(Category category) {
		categoryRepository.delete(category);
		return !categoryRepository.findById(category.getIdCategory()).isPresent();
	}

	@Override
	public boolean deleteById(Long idCategory) {
		categoryRepository.deleteById(idCategory);
		return !categoryRepository.findById(idCategory).isPresent();
	}

	@Override
	public boolean deleteAllByCategoryName(String categoryName) {
		return categoryRepository.deleteAllByCategoryName(categoryName);
	}

}
