package com.nvault.message.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.message.model.Message;
import com.nvault.message.repository.MessageRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	
	@Autowired
	MessageRepository messageRepository;

	@Override
	public List<Message> listAllMessages() {
		
		return messageRepository.findAll();
	}

	

}
