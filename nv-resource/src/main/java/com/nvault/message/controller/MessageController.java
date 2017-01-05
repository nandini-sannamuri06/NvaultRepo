package com.nvault.message.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.contact.model.Contact;
import com.nvault.message.model.Message;

import com.nvault.message.service.MessageService;
import com.nvault.model.NVaultUser;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;

	@RequestMapping(value = "/getMessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<Message>> listAllMessages() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    NVaultUser user = (NVaultUser)auth.getPrincipal();
		List<Message> messages = messageService.listAllMessages(user.getId());
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	@RequestMapping(value = "/AllMessagesInclArchieve", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<Message>> getMessages() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    NVaultUser user = (NVaultUser)auth.getPrincipal();
		List<Message> messages = messageService.getMessagesInclArchieve(user.getId());
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateMessage/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Message updateMessage(@PathVariable("id") Integer id) {

		Message message = messageService.updateMessage(id);

		return message;
	}

	@RequestMapping(value = "/updateMessageArchieve/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Message MessageArchieve(@PathVariable("id") Integer id) {

		Message message = messageService.MessageArchieve(id);

		return message;

	}

	@RequestMapping(value = "/getArchiveMessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<Message>> listAllArchiveMsgs() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    NVaultUser user = (NVaultUser)auth.getPrincipal();
		List<Message> messages = messageService.getArchiveMessages(user.getId());
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

	@RequestMapping(value = "/getTrashMessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<Message>> listAllTrashMessages() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    NVaultUser user = (NVaultUser)auth.getPrincipal();
		List<Message> messages = messageService.getTrashMessages(user.getId());
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}

}