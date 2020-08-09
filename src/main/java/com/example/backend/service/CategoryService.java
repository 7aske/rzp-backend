package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.service.impl.CategoryServiceImpl;

import java.util.List;

public interface CategoryService {

	List<Category> findAll();

	void delete(Category category) throws Exception;

	Category save(Category category) throws CategoryServiceImpl.CategoryValidationException;

	Category update(Category category) throws CategoryServiceImpl.CategoryValidationException;

	Category findById(Long idCategory);

	Category findByCategoryName(String categoryName);

	void deleteById(Long idCategory) throws Exception;

	void deleteAllByCategoryName(String categoryName) throws Exception;
}
