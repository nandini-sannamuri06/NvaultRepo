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



	    @RequestMapping(value = "/create", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	    public Contact registration(@RequestBody Contact contact) {
	 		contact.setUserId("1");
	    	contactService.saveContact(contact);
	    	return contact;
	    }
	    
//	    @RequestMapping(value = "/create", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
//	    public void registration(HttpServletRequest request) {
//	    	String fn = (String) request.getParameter("firstName");
//	    	String ln = (String) request.getParameter("lastName");
//	    	String em = (String) request.getParameter("emailId");
//	    	Contact contact = new Contact();
//	    	contact.setEmailId(em);
//	    	contact.setFirstName(fn);
//	    	contact.setLastName(ln);
//	    	contact.setUserId("1");
//	    	contactService.saveContact(contact);
//	    }


	    @RequestMapping(value = "/allContacts", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Contact>> getAllContacts() {
	    	List<Contact> contacts = contactService.getAllContacts();
	        return new ResponseEntity<List<Contact>> (contacts,HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/update", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	    public Contact updateContact( @RequestBody Contact contact) {
	    	contactService.updateContact(contact);
	        return contact;
	    }
	    
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void deleteContact(@RequestParam(value = "id", required = true) int id) {
	    	contactService.deleteContact(id);
	    }
}
