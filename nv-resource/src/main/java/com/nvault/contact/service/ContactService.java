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
	
	public void saveContact(Contact contact){
		contactRepository.save(contact);
	}
	
	public List<Contact> getAllContacts(){
		return contactRepository.findAll();
	}
	
	public void updateContact(Contact contact){
		contactRepository.save(contact);
	}
	
	public void deleteContact(int id){
		Contact contact = new Contact();
		contact.setId(id);
		contactRepository.delete(contact);
	}
}
