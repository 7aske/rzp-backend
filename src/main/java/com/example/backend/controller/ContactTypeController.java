
package com.example.backend.controller;

import com.example.backend.entity.ContactType;
import com.example.backend.entity.dto.http.ClientError;
import com.example.backend.entity.dto.http.ClientMessage;
import com.example.backend.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactType")
public class ContactTypeController {
	@Autowired
	private ContactTypeService contactTypeService;

	@GetMapping("/getAll")
	public ResponseEntity<List<ContactType>> getAll() {
		return ResponseEntity.ok(contactTypeService.findAll());
	}

	@GetMapping("/getById/{idContactType}")
	public ResponseEntity<ContactType> getById(@PathVariable Long idContactType) {
		return ResponseEntity.ok(contactTypeService.findById(idContactType));
	}


	@PostMapping("/save")
	public ResponseEntity<ContactType> save(@RequestBody ContactType contactType) {
		return ResponseEntity.ok(contactTypeService.save(contactType));
	}

	@PutMapping("/update")
	public ResponseEntity<ContactType> update(@RequestBody ContactType contactType) {
		return ResponseEntity.ok(contactTypeService.update(contactType));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody ContactType contactType) {
		try {
			contactTypeService.delete(contactType);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}

	@DeleteMapping("/deleteById/{idContactType}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idContactType) {
		try {
			contactTypeService.deleteById(idContactType);
			return ResponseEntity.ok(new ClientMessage("deleted"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ClientError(e.getMessage()));
		}
	}
}
