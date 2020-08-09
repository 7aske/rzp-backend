package com.example.backend.repository;

import com.example.backend.entity.ContactType;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	Optional<Contact> findByIdContact(Long idContact);
	List<Contact> findAllByIdContactType(ContactType idContactType);
	List<Contact> findAllByIdUser(User idUser);
	void deleteAllByIdContactType(ContactType idContactType);
	void deleteAllByIdUser(User idUser);
}
