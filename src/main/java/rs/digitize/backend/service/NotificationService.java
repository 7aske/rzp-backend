package rs.digitize.backend.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import rs.digitize.backend.entity.*;

public interface NotificationService {

	List<Notification> findAll(Specification<Notification> specification, Sort sort);

	List<Notification> findAllForUser(User user, Pageable pageable);

	void createForComment(Post post, Comment comment);

	Notification save(Notification notification);

	Notification update(Notification notification);

	Notification findById(Integer notificationId);

	void deleteById(Integer notificationId);

	void markAsRead(User user, Integer notificationId);
}