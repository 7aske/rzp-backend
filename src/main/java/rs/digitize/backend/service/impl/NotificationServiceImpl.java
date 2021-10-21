package rs.digitize.backend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.digitize.backend.entity.*;
import rs.digitize.backend.exception.http.HttpUnauthorizedException;
import rs.digitize.backend.repository.CommentRepository;
import rs.digitize.backend.repository.NotificationRepository;
import rs.digitize.backend.service.CommentService;
import rs.digitize.backend.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	private final NotificationRepository notificationRepository;
	private final CommentService commentService;

	public NotificationServiceImpl(NotificationRepository notificationRepository,
	                               @Lazy CommentService commentService) {
		this.notificationRepository = notificationRepository;
		this.commentService = commentService;
	}

	@Override
	public List<Notification> findAll(Specification<Notification> specification, Sort sort) {
		return notificationRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public List<Notification> findAllUnreadForUser(User user, Pageable pageable) {
		return notificationRepository.findAllByUserAndReadFalseOrderByCreatedDateDesc(user, pageable);
	}

	@Override
	public List<Notification> findAllForUser(User user, Pageable pageable) {
		return notificationRepository.findAllByUserOrderByCreatedDateDesc(user, pageable);
	}

	@Override
	public void createForComment(Post post, Comment comment) {
		User authenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Notification notification = new Notification();
		notification.setTitle(String.format("%s commented on post %s", comment.getUser().getDisplayName(), post.getTitle()));
		notification.setBody(comment.getBody());
		notification.setActionUrl("/posts/" + post.getSlug() + "#comments");
		commentService.findAllForPost(post)
				.stream()
				.map(Comment::getUser)
				.distinct()
				.filter(user -> !user.equals(authenticated))
				.forEach(user -> {
					Notification notificationCopy = new Notification(notification);
					notificationCopy.setUser(user);
					save(notificationCopy);
				});
		if (!post.getUser().equals(authenticated)) {
			Notification notificationCopy = new Notification(notification);
			notificationCopy.setUser(post.getUser());
			save(notificationCopy);
		}
	}

	@Override
	public Notification findById(Integer notificationId) {
		return notificationRepository.findById(notificationId)
				.orElseThrow(() -> new NoSuchElementException("NotificationService.notFound"));
	}

	@Override
	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public Notification update(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public void deleteById(Integer notificationId) {
		notificationRepository.deleteById(notificationId);
	}

	@Override
	public void markAsRead(User user, Integer notificationId) {
		Notification notification = findById(notificationId);
		if (!notification.getUser().equals(user))
			throw new HttpUnauthorizedException();
		notification.setRead(true);
		update(notification);
	}


}