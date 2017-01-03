package com.nvault.message.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvault.contact.model.Contact;
import com.nvault.message.model.Message;
import com.nvault.message.repository.MessageRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public List<Message> listAllMessages() {

		return messageRepository.getAllMessages();
	}

	@Override
	public List<Message> getMessagesInclArchieve() {

		return messageRepository.getMessagesInclArchie();
	}

	public Message updateMessage(Integer id) {
		System.out.println(id);
		Message message = messageRepository.findOne(id);
		if(message!=null){
		message.setTrash(1);
		message = messageRepository.save(message);
		}
		return message;
	}

	@Override
	public Message MessageArchieve(Integer id) {
		Message message = messageRepository.findOne(id);
		if(message!=null){
		message.setArchieved(1);
		message = messageRepository.save(message);
		}
		return message;
	}

	@Override
	public List<Message> getArchiveMessages() {

		return messageRepository.findByArchieved();
	}

	@Override
	public List<Message> getTrashMessages() {
		return messageRepository.findByTrash();
	}

	@Override
	public void saveMessage(Message message) {
		// TODO Auto-generated method stub
		messageRepository.saveAndFlush(message);
		
	}

}
