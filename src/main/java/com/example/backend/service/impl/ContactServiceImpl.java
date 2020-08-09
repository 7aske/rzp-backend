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
	public boolean delete(Contact contact) throws Exception {
		contactRepository.delete(contact);
		return !contactRepository.findById(contact.getIdContact()).isPresent();
	}

	@Override
	public void deleteById(Long idContact) throws Exception {
		contactRepository.deleteById(idContact);
	}

	@Override
	public void deleteAllByIdContactType(ContactType idContactType) throws Exception {
		contactRepository.deleteAllByIdContactType(idContactType);
	}

	@Override
	public void deleteAllByIdUser(User idUser) throws Exception {
		contactRepository.deleteAllByIdUser(idUser);
	}
}
