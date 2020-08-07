package com.example.backend.repository;

import com.example.backend.entity.Role;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	Optional<UserRole> findByIdRoleAndIdUser(Long idRole, Long idUser);
	List<UserRole> findAllByIdRole(Long idRole);
	List<UserRole> findAllByIdUser(Long idUser);
	boolean deleteByIdRoleAndIdUser(Long idRole, Long idUser);
	boolean deleteAllByIdRole(Long idRole);
	boolean deleteAllByIdUser(Long idUser);
}
