package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.digitize.backend.entity.*;
import rs.digitize.backend.service.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
	private final ContactService contactService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllContacts")
	public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(name = "q", required = false) Specification<Contact> specification, @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(contactService.findAll(specification, sort));
	}

	@GetMapping("/{contactId}")
	@ApiOperation(value = "", nickname = "getContactById")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer contactId) {
		return ResponseEntity.ok(contactService.findById(contactId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveContact")
	public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateContact")
	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.update(contact));
	}

	@DeleteMapping("/{contactId}")
	@ApiOperation(value = "", nickname = "deleteContactById")
	public void deleteContactById(@PathVariable Integer contactId) {
		contactService.deleteById(contactId);
	}

}

