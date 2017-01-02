package com.nvault.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.contact.model.Contact;
import com.nvault.contact.repository.ContactRepository;
@Service
public class ContactService {
	@Autowired
	ContactRepository contactRepository;
	
	public Contact saveContact(Contact contact){
		return contactRepository.save(contact);
	}
	
	public List<Contact> getAllContacts(String userId){
		return contactRepository.findAll(userId);
	}
	
	public Contact updateContact(Contact contact){
		return contactRepository.save(contact);
	}
	
	public void deleteContact(int id){
		Contact contact = new Contact();
		contact.setId(id);
		contactRepository.delete(contact);
	}
}