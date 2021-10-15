package rs.digitize.backend.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import rs.digitize.backend.entity.*;
import rs.digitize.backend.exception.http.HttpForbiddenException;
import rs.digitize.backend.repository.ContactRepository;
import rs.digitize.backend.service.ContactService;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
	private final ContactRepository contactRepository;

	@Override
	public List<Contact> findAll(Specification<Contact> specification, Sort sort) {
		return contactRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public Contact findById(Integer contactId) {
		return contactRepository.findById(contactId)
				.orElseThrow(() -> new NoSuchElementException("ContactService.notFound"));
	}

	@Override
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Contact update(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public void deleteById(Integer contactId) {
		Contact contact = findById(contactId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user.isAdmin() || user.equals(contact.getUser()))
			contactRepository.deleteById(contactId);
		else
			throw new HttpForbiddenException();
	}


}