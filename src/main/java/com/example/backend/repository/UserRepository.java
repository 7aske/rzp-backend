package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.example.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// Optional<User> findByIdUser(Long idUser);
	Optional<User> findByUserUsername(String userUsername);
	Optional<User> findByUserEmail(String userEmail);
	// List<User> findAllByUserPassword(String userPassword);
	// List<User> findAllByUserFirstName(String userFirstName);
	// List<User> findAllByUserLastName(String userLastName);
	// List<User> findAllByUserAddress(String userAddress);
	// List<User> findAllByUserAbout(String userAbout);
	// List<User> findAllByUserDisplayName(String userDisplayName);
	// List<User> findAllByUserDateCreated(LocalDate userDateCreated);
}
