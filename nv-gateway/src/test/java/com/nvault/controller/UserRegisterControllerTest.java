package com.nvault.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.nvault.model.User;
import com.nvault.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserRegisterControllerTest {

	@Mock
	public UserService userService;

	@InjectMocks
	public UserRegisterController userController;

	public MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testRegsiterUser() throws Exception {
		Mockito.when(userService.findById(Matchers.anyInt())).thenReturn(null);
		User user = new User();
		user.setId(10);
		Mockito.when(userService.saveUser(Matchers.any(User.class))).thenReturn(user);
		String json = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/register").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(201)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void testRegsiterUserWithDataExists() throws Exception {
		User user = new User();
		user.setId(10);
		Mockito.when(userService.findById(Matchers.anyInt())).thenReturn(user);
		Mockito.when(userService.saveUser(Matchers.any(User.class))).thenReturn(null);
		String json = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/register").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(400));
	}

}
