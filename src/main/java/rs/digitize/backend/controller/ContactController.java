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
import rs.digitize.backend.entity.domain.ContactType;
import rs.digitize.backend.service.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
	private final ContactService contactService;

	@DeleteMapping("/{contactId}")
	@ApiOperation(value = "", nickname = "deleteContactById")
	public void deleteContactById(@PathVariable Integer contactId) {
		contactService.deleteById(contactId);
	}

	@GetMapping("/types")
	@ApiOperation(value = "", nickname = "getAllContactTypes")
	public ResponseEntity<Object[]> getAllContactTypes(){
		return ResponseEntity.ok(ContactType.values());
	}
}

