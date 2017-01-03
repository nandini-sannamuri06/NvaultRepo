package com.nvault.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvault.model.NVaultUser;
import com.nvault.model.PasswordDetails;
import com.nvault.model.Role;
import com.nvault.service.PwdDtlsService;
import com.nvault.service.UserService;
import com.nvault.util.ResetPassword;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GateWayControllerTest {

	public MockMvc mockMvc;

	@InjectMocks
	public GatewayController gatewayController;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	@Mock
	UserService userService;

	@Mock
	PwdDtlsService pwdDtlsService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(gatewayController)
				.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain)).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUser() throws Exception {

		NVaultUser user = new NVaultUser();
		user.setUsername("nandini");
		user.setPassword("nandini");
		user.setAccountNonExpired(true);
		Role role = new Role();
		role.setId(1);
		role.setCode("ROLE_USER");
		role.setLabel("user");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		Authentication authToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		mockMvc.perform(get("/user")).andExpect(status().isOk());
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

	@Test
	public void testGetEmailDtlsWithNull() throws Exception {
		Mockito.when(userService.findByEmailID(Matchers.anyString())).thenReturn(null);
		mockMvc.perform(get("/checkEmail").param("email", "xxx@gmail.com"))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testGetEmailDtls() throws Exception {
		NVaultUser user = new NVaultUser();
		user.setUsername("nandini");
		user.setPassword("nandini");
		user.setAccountNonExpired(true);
		Mockito.when(userService.findByEmailID(Matchers.anyString())).thenReturn(user);
		mockMvc.perform(get("/checkEmail").param("email", "xxx@gmail.com"))
				.andExpect(status().isOk());

	}
	
	@Test
	public void testResetPwdWithExistLink() throws Exception {
		PasswordDetails pwdDtls = new PasswordDetails();
		pwdDtls.setId(10);
		pwdDtls.setUniqueId("111222");
		pwdDtls.setExpired(1);
		Mockito.when(pwdDtlsService.getPwdDtls(Matchers.anyString())).thenReturn(pwdDtls);
		ResetPassword resetPwd = new ResetPassword();
		resetPwd.setMail("xxx");
		String obj = new ObjectMapper().writeValueAsString(resetPwd);
		mockMvc.perform(post("/resetPwd").content(obj).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isConflict());
	}
	
	@Test
	public void testResetPwdWithUpdated() throws Exception {
		NVaultUser user = new NVaultUser();
		user.setUsername("nandini");
		user.setPassword("nandini");
		user.setAccountNonExpired(true);
		PasswordDetails pwdDtls = new PasswordDetails();
		pwdDtls.setId(10);
		pwdDtls.setUniqueId("111222");
		pwdDtls.setExpired(0);
		Mockito.when(pwdDtlsService.getPwdDtls(Matchers.anyString())).thenReturn(pwdDtls);
        Mockito.when(userService.updatePassword(Matchers.anyString(), Matchers.anyString())).thenReturn(user);
        Mockito.when(pwdDtlsService.savePwdDtls(pwdDtls)).thenReturn(pwdDtls);
		ResetPassword resetPwd = new ResetPassword();
		resetPwd.setMail("xxx");
		String obj = new ObjectMapper().writeValueAsString(resetPwd);
		mockMvc.perform(post("/resetPwd").content(obj).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
	@Test
	public void testResetPwdWithOutUser() throws Exception {
		PasswordDetails pwdDtls = new PasswordDetails();
		pwdDtls.setId(10);
		pwdDtls.setUniqueId("111222");
		pwdDtls.setExpired(0);
		Mockito.when(pwdDtlsService.getPwdDtls(Matchers.anyString())).thenReturn(pwdDtls);
        Mockito.when(userService.updatePassword(Matchers.anyString(), Matchers.anyString())).thenReturn(null);
		ResetPassword resetPwd = new ResetPassword();
		resetPwd.setMail("xxx");
		String obj = new ObjectMapper().writeValueAsString(resetPwd);
		mockMvc.perform(post("/resetPwd").content(obj).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
	}

}
