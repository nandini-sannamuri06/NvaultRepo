package com.nvault.security;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nvault.model.Role;
import com.nvault.model.NVaultUser;
import com.nvault.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CustomUserDetailsServiceTest {
	
	@Mock
	public UserService userService;
	
	@InjectMocks
	public CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
	
	
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testLoadByUserNameWithNull(){
		Mockito.when(userService.findByUserName(Matchers.anyString())).thenReturn(null);
		customUserDetailsService.loadUserByUsername("nVault");
	}
	
	@Test
	public void testLoadByUserName(){
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
		Mockito.when(userService.findByUserName(Matchers.anyString())).thenReturn(user);
		UserDetails userDetails = customUserDetailsService.loadUserByUsername("nVault");
        Assert.assertEquals(userDetails.getUsername(), user.getUsername());	
        Assert.assertEquals(userDetails.getPassword(), user.getPassword());	
	}

}
