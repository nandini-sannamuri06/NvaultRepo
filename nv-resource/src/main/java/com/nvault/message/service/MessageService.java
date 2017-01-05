package com.nvault.message.service;

import java.util.List;
import com.nvault.message.model.Message;

public interface MessageService {

	List<Message> listAllMessages(Integer userId);

	Message updateMessage(Integer id);

	Message MessageArchieve(Integer id);

	List<Message> getArchiveMessages(Integer userId);

	List<Message> getTrashMessages(Integer userId);

	List<Message> getMessagesInclArchieve(Integer userId);
	
	Message saveMessage(Message message);
}
