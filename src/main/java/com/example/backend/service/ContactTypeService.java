package com.example.backend.service;

import com.example.backend.entity.ContactType;

import java.util.List;

public interface ContactTypeService {

	List<ContactType> findAll();

	boolean delete(ContactType contactType);

	ContactType save(ContactType contactType);

	ContactType update(ContactType contactType);

	ContactType findById(Long idContactType);

	List<ContactType> findAllByContactTypeName(String contactTypeName);

	boolean deleteById(Long idContactType);

	boolean deleteAllByContactTypeName(String contactTypeName);
}
