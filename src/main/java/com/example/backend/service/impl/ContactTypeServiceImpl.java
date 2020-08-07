package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entity.ContactType;
import com.example.backend.repository.ContactTypeRepository;
import com.example.backend.service.ContactTypeService;
import java.util.List;

@Service
public class ContactTypeServiceImpl implements ContactTypeService {

	@Autowired
	private ContactTypeRepository contactTypeRepository;

	@Override
	public List<ContactType> findAll() {
		return contactTypeRepository.findAll();
	}

	@Override
	public ContactType findById(Long idContactType) {
		if (contactTypeRepository.findById(idContactType).isPresent()) {
			return contactTypeRepository.findById(idContactType).get();
		} else {
			return null;
		}
	}

	@Override
	public List<ContactType> findAllByContactTypeName(String contactTypeName) {
		return contactTypeRepository.findAllByContactTypeName(contactTypeName);
	}

	@Override
	public ContactType save(ContactType contactType) {
		return contactTypeRepository.save(contactType);
	}

	@Override
	public ContactType update(ContactType contactType) {
		return contactTypeRepository.save(contactType);
	}

	@Override
	public boolean delete(ContactType contactType) {
		contactTypeRepository.delete(contactType);
		return !contactTypeRepository.findById(contactType.getIdContactType()).isPresent();
	}

	@Override
	public boolean deleteById(Long idContactType) {
		contactTypeRepository.deleteById(idContactType);
		return !contactTypeRepository.findById(idContactType).isPresent();
	}

	@Override
	public boolean deleteAllByContactTypeName(String contactTypeName) {
		return false;
	}

}
