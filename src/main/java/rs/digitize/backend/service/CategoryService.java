package rs.digitize.backend.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.*;

public interface CategoryService {

	List<Category> findAll(Specification<Category> specification, Sort sort);

	Category save(Category category);

	Category update(Category category);

	Category findById(Integer categoryId);

	void deleteById(Integer categoryId);

}