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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nvault.model.PasswordDetails;
import com.nvault.repository.PasswordDtlsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PwdDtlsServiceImplTest {
	
	@Mock
	PasswordDtlsRepository pwDtlsRepository;
	
	@InjectMocks
	PwdDtlsServiceImpl pwdDtlsServiceImpl = new PwdDtlsServiceImpl();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetPwdDtls(){
		PasswordDetails pwdDtls = new PasswordDetails();
		pwdDtls.setExpired(1);
		pwdDtls.setId(1);
		pwdDtls.setUniqueId("xxxx12sed");
		Mockito.when(pwDtlsRepository.findByUniqueId(Matchers.anyString())).thenReturn(pwdDtls);
		PasswordDetails result = pwdDtlsServiceImpl.getPwdDtls("xxxx");
        Assert.assertEquals(result.getUniqueId(), pwdDtls.getUniqueId());	
	}
	
	@Test
	public void testSavePwdDtls(){
		PasswordDetails pwdDtls = new PasswordDetails();
		pwdDtls.setExpired(1);
		pwdDtls.setId(1);
		pwdDtls.setUniqueId("xxxx12sed");
		Mockito.when(pwDtlsRepository.save(Matchers.any(PasswordDetails.class))).thenReturn(pwdDtls);
		PasswordDetails result = pwdDtlsServiceImpl.savePwdDtls(pwdDtls);
        Assert.assertEquals(result.getUniqueId(), pwdDtls.getUniqueId());	
	}

}
