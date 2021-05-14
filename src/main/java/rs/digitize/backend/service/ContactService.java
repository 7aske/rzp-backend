package rs.digitize.backend.service;

import rs.digitize.backend.entity.Contact;

import java.util.List;

public interface ContactService {

	List<Contact> findAll();

	Contact save(Contact contact);

	Contact update(Contact contact);

	Contact findById(Integer contactId);

	void deleteById(Integer contactId);

}