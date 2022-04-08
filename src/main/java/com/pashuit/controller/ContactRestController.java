package com.pashuit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pashuit.constants.AppConstants;
import com.pashuit.entity.Contact;
import com.pashuit.properties.AppProperties;
import com.pashuit.service.ContactService;

@RestController
public class ContactRestController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AppProperties appProps;

	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact) {
		boolean status = contactService.saveContact(contact);
		
		Map<String, String> message = appProps.getMessage();
		if (status) {
			return message.get(AppConstants.CONTACT_SAVE_SUCC);
		}
		return message.get(AppConstants.CONTACT_SAVE_FAIL);
	}

	@GetMapping("/contact/{cid}")
	public Contact editContact(@PathVariable("cid") Integer contactId) {
		return contactService.getContactById(contactId);
	}

	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}

	@DeleteMapping("/contact/{cid}")
	public String deleteContact(@PathVariable("cid") Integer contactId) {
		boolean status = contactService.deleteContactById(contactId);
		
		Map<String, String> message = appProps.getMessage();
		if (status) {
			return message.get(AppConstants.CONTACT_DELETE_SUCC);
		} else {
			return message.get(AppConstants.CONTACT_DELETE_FAIL);
		}
	}

}
