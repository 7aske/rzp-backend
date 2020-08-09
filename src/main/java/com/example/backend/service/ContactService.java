package com.example.backend.service;

import com.example.backend.entity.Contact;
import com.example.backend.entity.ContactType;
import com.example.backend.entity.User;

import java.util.List;

public interface ContactService {

	List<Contact> findAll();

	boolean delete(Contact contact) throws Exception;

	Contact save(Contact contact);

	Contact update(Contact contact);

	Contact findById(Long idContact);

	List<Contact> findAllByIdContactType(ContactType idContactType);

	List<Contact> findAllByIdUser(User idUser);

	List<Contact> findAllByContactValue(String contactValue);

	void deleteById(Long idContact) throws Exception;

	void deleteAllByIdContactType(ContactType idContactType) throws Exception;

	void deleteAllByIdUser(User idUser) throws Exception;
}
