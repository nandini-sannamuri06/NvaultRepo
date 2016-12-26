package com.nvault.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

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
import com.nvault.model.NVaultUser;
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
	public void testRegsiterUser() throws Exception{
	Mockito.when(userService.findById(Matchers.anyInt())).thenReturn(null);
	NVaultUser user = new NVaultUser();
	user.setId(10);
	user.setUsername("xxx");
	Mockito.when(userService.saveUser(Matchers.any(NVaultUser.class))).thenReturn(user);
	String json = new ObjectMapper().writeValueAsString(user);
	mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
			.andExpect(status().is(201));
	}

	@Test
	public void testRegsiterUserWithDataError() throws Exception {
		
		NVaultUser user = new NVaultUser();
		user.setId(10);
		user.setUsername("xxx");
		Mockito.when(userService.findById(Matchers.anyInt())).thenReturn(null);
		Mockito.when(userService.saveUser(Matchers.any(NVaultUser.class))).thenReturn(null);
		String json = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().is(400));
	}
	@Test
	public void testRegsiterUserWithDataExists() throws Exception {
		
		NVaultUser user = new NVaultUser();
		user.setId(10);
		user.setUsername("xxx");
		Mockito.when(userService.findById(Matchers.anyInt())).thenReturn(user);
		String json = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().is(400));
	}

}
