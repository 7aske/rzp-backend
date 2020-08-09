package com.example.backend.service;

import com.example.backend.entity.ContactType;

import java.util.List;

public interface ContactTypeService {

	List<ContactType> findAll();

	void delete(ContactType contactType) throws Exception;

	ContactType save(ContactType contactType);

	ContactType update(ContactType contactType);

	ContactType findById(Long idContactType);

	List<ContactType> findAllByContactTypeName(String contactTypeName);

	void deleteById(Long idContactType) throws Exception;

	void deleteAllByContactTypeName(String contactTypeName) throws Exception;
}
