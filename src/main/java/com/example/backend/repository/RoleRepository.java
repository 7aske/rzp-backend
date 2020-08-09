package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.backend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByIdRole(Long idRole);
	Optional<Role> findByRoleName(String roleName);
	@Query("select r from Role r left join UserRole ur on r.idRole = ur.idRole left join User u on u.idUser = ur.idUser where u.userUsername = :userUsername")
	List<Role> findAllByUserUsername(String userUsername);
	void deleteByRoleName(String roleName);
}
