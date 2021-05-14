package rs.digitize.backend.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import rs.digitize.backend.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer>, JpaSpecificationExecutor<Media> {
	Optional<Media> findByUri(String uri);
}