package rs.digitize.backend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import rs.digitize.backend.entity.Notification;
import rs.digitize.backend.entity.User;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>, JpaSpecificationExecutor<Notification> {
	List<Notification> findAllByUserAndReadFalseOrderByCreatedDateDesc(User user, Pageable pageable);
	List<Notification> findAllByUserOrderByCreatedDateDesc(User user, Pageable pageable);
}