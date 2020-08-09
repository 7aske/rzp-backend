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
	public void delete(ContactType contactType) throws Exception {
		contactTypeRepository.delete(contactType);
	}

	@Override
	public void deleteById(Long idContactType) throws Exception {
		contactTypeRepository.deleteById(idContactType);
	}

	@Override
	public void deleteAllByContactTypeName(String contactTypeName) throws Exception {
		contactTypeRepository.deleteByContactTypeName(contactTypeName);
	}

}
