package com.nvault.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nvault.model.Role;
import com.nvault.model.User;
import com.nvault.security.CustomUserDetailsService;
import com.nvault.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GateWayControllerTest {

	public MockMvc mockMvc;

	@InjectMocks
	public GatewayController gatewayController;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(gatewayController)
				.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain)).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUser() throws Exception {

		User user = new User();
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
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (Role r : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(r.getCode()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), user.isAccountNonExpired(), true, true, true, grantedAuthorities);
		Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		mockMvc.perform(get("/user")).andExpect(status().isOk());
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

}
