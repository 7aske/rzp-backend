package com.example.backend.service;

import com.example.backend.entity.Category;
import java.util.List;

public interface CategoryService {

	List<Category> findAll();

	boolean delete(Category category);

	Category save(Category category);

	Category update(Category category);

	Category findById(Long idCategory);

	List<Category> findAllByCategoryName(String categoryName);

	boolean deleteById(Long idCategory);

	boolean deleteAllByCategoryName(String categoryName);
}
