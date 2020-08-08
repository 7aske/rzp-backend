
package com.example.backend.controller;

import com.example.backend.entity.Contact;
import com.example.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Contact>> getAll() {
		return ResponseEntity.ok(contactService.findAll());
	}

	@GetMapping("/getById/{idContact}")
	public ResponseEntity<Contact> getById(@PathVariable Long idContact) {
		return ResponseEntity.ok(contactService.findById(idContact));
	}


	@PostMapping("/save")
	public ResponseEntity<Contact> save(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.save(contact));
	}

	@PutMapping("/update")
	public ResponseEntity<Contact> update(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.update(contact));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.delete(contact));
	}

	@DeleteMapping("/deleteById/{idContact}")
	public ResponseEntity<Object> deleteById(@PathVariable Long idContact) {
		return ResponseEntity.ok(contactService.deleteById(idContact));
	}
}