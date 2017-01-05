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
	public List<Message> listAllMessages(Integer userId) {

		return messageRepository.getAllMessages(userId);
	}

	@Override
	public List<Message> getMessagesInclArchieve(Integer userId) {

		return messageRepository.getMessagesInclArchie(userId);
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
	public List<Message> getArchiveMessages(Integer userId) {

		return messageRepository.findByArchieved(userId);
	}

	@Override
	public List<Message> getTrashMessages(Integer userId) {
		return messageRepository.findByTrash(userId);
	}

	@Override
	public Message saveMessage(Message message) {
		// TODO Auto-generated method stub
		Message returnMessage=messageRepository.saveAndFlush(message);
		return returnMessage;
		
	}

}
