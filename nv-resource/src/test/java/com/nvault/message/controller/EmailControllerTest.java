package com.nvault.message.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvault.message.model.Message;
import com.nvault.message.service.PwdDtlsService;
import com.nvault.message.util.EmailSenderUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmailControllerTest {
	
	@Mock
	public EmailSenderUtil emailSenderUtil;
	
	@InjectMocks
	public EmailController emailController;
	
	public MockMvc mockMvc;
	
	@Mock
	public PwdDtlsService pwdDtlsService;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
	}
	
	//@Test
	public void testSendEmailWithFailure() throws Exception{
		Mockito.when(emailSenderUtil.sendMail(Matchers.any(Message.class))).thenReturn("failure");
		Message message = new Message();
		message.setBody("xxxx?id=1111");
		String jsonObj = new ObjectMapper().writeValueAsString(message);
		mockMvc.perform(post("/sendMail").content(jsonObj).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
		
	}
	@Test
	public void testSendEmailWithSuccess() throws Exception{
		Mockito.when(emailSenderUtil.sendMail(Matchers.any(Message.class))).thenReturn("success");
		Message message = new Message();
		message.setBody("xxxx?id=1111");
		String jsonObj = new ObjectMapper().writeValueAsString(message);
		mockMvc.perform(post("/sendMail").content(jsonObj).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		
	}

}
