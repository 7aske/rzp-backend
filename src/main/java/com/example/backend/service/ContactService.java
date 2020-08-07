package com.example.backend.service;

import com.example.backend.entity.Contact;
import com.example.backend.entity.ContactType;
import com.example.backend.entity.User;

import java.util.List;

public interface ContactService {

	List<Contact> findAll();

	boolean delete(Contact contact);

	Contact save(Contact contact);

	Contact update(Contact contact);

	Contact findById(Long idContact);

	List<Contact> findAllByIdContactType(ContactType idContactType);

	List<Contact> findAllByIdUser(User idUser);

	List<Contact> findAllByContactValue(String contactValue);

	boolean deleteById(Long idContact);

	boolean deleteAllByIdContactType(ContactType idContactType);

	boolean deleteAllByIdUser(User idUser);
}
