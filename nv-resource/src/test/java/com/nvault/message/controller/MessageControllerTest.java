package com.nvault.message.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.nvault.message.model.Message;
import com.nvault.message.service.MessageService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageControllerTest {

	public MockMvc mockMvc;
	@Mock
	public MessageService messageService;

	@InjectMocks
	public MessageController messageController;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}

	@Test
	public void testListMessages() throws Exception {
		Mockito.when(messageService.listAllMessages()).thenReturn(new ArrayList<Message>());
		mockMvc.perform(get("/getMessages")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}

