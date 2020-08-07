package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.ContactType;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
	Optional<ContactType> findByIdContactType(Long idContactType);
	List<ContactType> findAllByContactTypeName(String contactTypeName);
	List<ContactType> findAllByContactTypeValueType(String contactTypeValueType);
	boolean deleteByContactTypeName(String contactTypeName);
	boolean deleteByIdContactType(Long idContactType);
}
