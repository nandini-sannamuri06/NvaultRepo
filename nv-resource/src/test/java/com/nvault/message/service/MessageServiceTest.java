package com.nvault.message.service;
import java.util.ArrayList;
import java.util.Collection;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.nvault.message.model.Message;
import com.nvault.message.repository.MessageRepository;



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
	public void testFindAll(){
		Mockito.when(messageRepository.findAll()).thenReturn(new ArrayList<Message>());
		Collection<Message> messageObjs = messageServiceImpl.listAllMessages();
		Assert.assertEquals(messageObjs.size(), 0);
	}


}

