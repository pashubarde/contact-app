package com.pashuit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pashuit.entity.Contact;
import com.pashuit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact savedContact = contactRepository.save(contact);
		if (savedContact.getContactId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact contact = new Contact();
		contact.setActiveSw("Y");
		return contactRepository.findAll(Example.of(contact));

	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> existingContact = contactRepository.findById(contactId);
		if (existingContact.isPresent()) {
			return existingContact.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		Optional<Contact> existingContact = contactRepository.findById(contactId);
		if (existingContact.isPresent()) {
			Contact contact = existingContact.get();
			contact.setActiveSw("N");
			contactRepository.save(contact);
			return true;
		}
		return false;
	}
}
