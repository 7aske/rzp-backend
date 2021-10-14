package rs.digitize.backend.service;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.*;

public interface ContactService {

	List<Contact> findAll(Specification<Contact> specification, Sort sort);

	Contact save(Contact contact);

	Contact update(Contact contact);

	Contact findById(Integer contactId);

	void deleteById(Integer contactId);

}