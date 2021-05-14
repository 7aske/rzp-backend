package rs.digitize.backend.service;

import rs.digitize.backend.entity.Category;

import java.util.List;

public interface CategoryService {

	List<Category> findAll();

	Category save(Category category);

	Category update(Category category);

	Category findById(Integer categoryId);

	void deleteById(Integer categoryId);

}