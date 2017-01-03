package com.nvault.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nvault.model.NVaultUser;
import com.nvault.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserServiceImplTest {

	@Mock
	public UserRepository userRepository;

	@Mock
	public PasswordEncoder passwordEncoder;

	@InjectMocks
	public UserServiceImpl userServiceImpl = new UserServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSave() throws Exception {
		NVaultUser user = new NVaultUser();
		user.setId(25);
		user.setUsername("xxxx");
		user.setMail("mail");
		Mockito.when(passwordEncoder.encode(Matchers.any(CharSequence.class))).thenReturn("value");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		NVaultUser userSave = userServiceImpl.saveUser(user);
		Assert.assertEquals(Integer.valueOf(userSave.getId()), Integer.valueOf(25));
		Assert.assertEquals(userSave.getUsername(), "xxxx");
	}

	@Test
	public void testFindByUserName() {
		NVaultUser user = new NVaultUser();
		user.setUsername("nandini");
		user.setPassword("nandini");
		user.setAccountNonExpired(true);
		Mockito.when(userRepository.findByUsername(Matchers.anyString())).thenReturn(user);
		NVaultUser userDtls = userServiceImpl.findByUserName("nVault");

		Assert.assertEquals(user.getUsername(), userDtls.getUsername());
	}

	@Test
	public void testFindByEmailId() {
		NVaultUser user = new NVaultUser();
		user.setUsername("nandini");
		user.setPassword("nandini");
		user.setMail("mail");
		user.setAccountNonExpired(true);
		Mockito.when(userRepository.findByMail(Matchers.anyString())).thenReturn(user);
		NVaultUser result = userServiceImpl.findByEmailID("xxx");
		Assert.assertEquals(result.getPassword(), user.getPassword());
	}
	
	@Test
	public void testUpdatePwdWithNull() throws Exception{
		String encryptPwd = "xxx123edccc";
		Mockito.when(passwordEncoder.encode(Matchers.any(CharSequence.class))).thenReturn(encryptPwd);
		Mockito.when(userRepository.findByMail(Matchers.anyString())).thenReturn(null);
		NVaultUser user = userServiceImpl.updatePassword("xxx", "xxx");
	    Assert.assertEquals(user, null);
	}
	
	@Test
	public void testUpdatePwd() throws Exception{
		NVaultUser user = new NVaultUser();
		user.setUsername("nandini");
		user.setPassword("nandini");
		user.setMail("mail");
		user.setAccountNonExpired(true);
		String encryptPwd = "xxx123edccc";
		Mockito.when(passwordEncoder.encode(Matchers.any(CharSequence.class))).thenReturn(encryptPwd);
		Mockito.when(userRepository.findByMail(Matchers.anyString())).thenReturn(user);
		Mockito.when(userRepository.save(Matchers.any(NVaultUser.class))).thenReturn(user);
		NVaultUser result = userServiceImpl.updatePassword("xxx", "xxx");
	    Assert.assertEquals(encryptPwd, result.getPassword());
	}

}
