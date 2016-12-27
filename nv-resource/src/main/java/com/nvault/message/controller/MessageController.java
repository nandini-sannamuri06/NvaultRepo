package com.nvault.message.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.message.model.Message;

import com.nvault.message.service.MessageService;
@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	@RequestMapping(value = "/getMessages", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    
	public ResponseEntity<List<Message>> listAllMessages() {
    	List<Message> messages = messageService.listAllMessages();
        return new ResponseEntity<List<Message>> (messages,HttpStatus.OK);
    }
	
	
}
