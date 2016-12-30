package com.nvault.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.contact.model.Contact;
import com.nvault.contact.service.ContactService;

@RestController
public class ContactController {
	
	 	@Autowired
	    private ContactService contactService;



	    @RequestMapping(value = "/create", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Contact> registration(@RequestBody Contact contact) {
	 		contact.setUserId("1");
	 		Contact contactResp = contactService.saveContact(contact);
	    	return new ResponseEntity<Contact> (contactResp,HttpStatus.OK);
	    }
	    

	    @RequestMapping(value = "/allContacts", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Contact>> getAllContacts() {
	    	List<Contact> contacts = contactService.getAllContacts();
	        return new ResponseEntity<List<Contact>> (contacts,HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/update", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Contact> updateContact( @RequestBody Contact contact) {
	    	Contact contactResp = contactService.updateContact(contact);
	    	return new ResponseEntity<Contact> (contactResp,HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void deleteContact(@RequestParam(value = "id", required = true) int id) {
	    	contactService.deleteContact(id);
	    }
}
