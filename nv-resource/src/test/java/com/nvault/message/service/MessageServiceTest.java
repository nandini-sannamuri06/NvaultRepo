package com.nvault.message.service;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.nvault.message.model.Message;
import com.nvault.message.repository.MessageRepository;
import com.nvault.model.NVaultUser;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageServiceTest {

	@Mock
	public MessageRepository messageRepository;
	
	@InjectMocks
	public MessageServiceImpl messageServiceImpl = new MessageServiceImpl();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetMessages(){
		Message message = new Message();
		Mockito.when(messageRepository.findAll()).thenReturn(new ArrayList<Message>());
		Collection<Message> messageObjs = messageServiceImpl.listAllMessages(Matchers.anyInt());
		Assert.assertEquals(messageObjs.size(), 0);
	}
	
	@Test
	public void testGetAllMessages(){
		Message message = new Message();
		
		Mockito.when(messageRepository.findAll()).thenReturn(new ArrayList<Message>());
		Collection<Message> messageObjs = messageServiceImpl.getMessagesInclArchieve(Matchers.anyInt());
		Assert.assertEquals(messageObjs.size(), 0);
	}
	
	@Test
	public void testTrashMessages(){
		Message message = new Message();
		
		Mockito.when(messageRepository.findAll()).thenReturn(new ArrayList<Message>());
		Collection<Message> messageObjs = messageServiceImpl.getTrashMessages(Matchers.anyInt());
		Assert.assertEquals(messageObjs.size(), 0);
	}
	
	@Test
	public void testArchievedMessages(){
		Message message = new Message();
		Mockito.when(messageRepository.findAll()).thenReturn(new ArrayList<Message>());
		Collection<Message> messageObjs = messageServiceImpl.getArchiveMessages(Matchers.anyInt());
		Assert.assertEquals(messageObjs.size(), 0);
	}
	
	@Test
	public void updateMessageTrashWithNull() throws Exception {
		
		Mockito.when(messageRepository.findOne(Matchers.anyInt())).thenReturn(null);
		Message message = messageServiceImpl.updateMessage(10);
		
	}
	
	@Test
	public void updateMessageTrash() throws Exception {
		Message message = new Message();
		Mockito.when(messageRepository.findOne(Matchers.anyInt())).thenReturn(message);
		Message messageUpdate = messageServiceImpl.updateMessage(10);
		Assert.assertEquals(message.getTrash(), 1);
		
	}
	@Test
	public void updateMessageArchieveWithNull() throws Exception {
		Mockito.when(messageRepository.findOne(Matchers.anyInt())).thenReturn(null);
		Message message = messageServiceImpl.MessageArchieve(10);
		
	}
	
	@Test
	public void updateMessageArchieve() throws Exception {
		Message message = new Message();
		Mockito.when(messageRepository.findOne(Matchers.anyInt())).thenReturn(message);
		Message messageUpdate = messageServiceImpl.MessageArchieve(10);
		Assert.assertEquals(message.getArchieved(), 1);
		
	}
}

