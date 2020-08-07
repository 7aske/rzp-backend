package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByIdCategory(Long idCategory);
	List<Category> findAllByCategoryName(String categoryName);
	boolean deleteAllByCategoryName(String categoryName);
}
