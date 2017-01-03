package com.nvault.message.service;

import java.util.List;
import com.nvault.message.model.Message;

public interface MessageService {

	List<Message> listAllMessages();

	Message updateMessage(Integer id);

	Message MessageArchieve(Integer id);

	List<Message> getArchiveMessages();

	List<Message> getTrashMessages();

	List<Message> getMessagesInclArchieve();
	
	void saveMessage(Message message);
}
