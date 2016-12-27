package com.nvault.message.service;

import java.util.List;
import com.nvault.message.model.Message;

public interface MessageService {
	
	 List<Message> listAllMessages();
	
	Message getMessageById(int id);

}
