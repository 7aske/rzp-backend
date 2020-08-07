package com.example.backend.service.impl;

import com.example.backend.entity.ContactType;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Contact;
import com.example.backend.repository.ContactRepository;
import com.example.backend.service.ContactService;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public Contact findById(Long idContact) {
		if (contactRepository.findById(idContact).isPresent()) {
			return contactRepository.findById(idContact).get();
		} else {
			return null;
		}
	}

	@Override
	public List<Contact> findAllByIdContactType(ContactType idContactType) {
		return null;
	}

	@Override
	public List<Contact> findAllByIdUser(User idUser) {
		return null;
	}

	@Override
	public List<Contact> findAllByContactValue(String contactValue) {
		return null;
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Contact update(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public boolean delete(Contact contact) {
		contactRepository.delete(contact);
		return !contactRepository.findById(contact.getIdContact()).isPresent();
	}

	@Override
	public boolean deleteById(Long idContact) {
		contactRepository.deleteById(idContact);
		return !contactRepository.findById(idContact).isPresent();
	}

	@Override
	public boolean deleteAllByIdContactType(ContactType idContactType) {
		return contactRepository.deleteAllByIdContactType(idContactType);
	}

	@Override
	public boolean deleteAllByIdUser(User idUser) {
		return contactRepository.deleteAllByIdUser(idUser);
	}
}
