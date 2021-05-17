package rs.digitize.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.digitize.backend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}