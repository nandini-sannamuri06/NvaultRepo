package com.nvault.message.util;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nvault.message.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EmailSenderUtilTest {

	@Mock
	JavaMailSender mailSender;

	@Mock
	VelocityEngine velocityEngine;

	@InjectMocks
	EmailSenderUtil emailSenderUtil = new EmailSenderUtil();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testSendMail() {
		MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
		Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
		Template template = Mockito.mock(Template.class);
		Mockito.when(velocityEngine.getTemplate(Matchers.anyString())).thenReturn(template);
		Message message = new Message();
		message.setSender("xxx");
		message.setRecipient("yyy");
		message.setSubject("RestPwd");
		message.setBody("body");
		String status = emailSenderUtil.sendMail(message);
		Assert.assertEquals(status, "success");
	}

	@Test
	public void testSendMailWithException() {
		String status = emailSenderUtil.sendMail(new Message());
		Assert.assertEquals(status, "failure");
	}
	
	@Test
	public void testSendMailWithTmplateNull() {
		MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
		Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
		Message message = new Message();
		message.setSender("xxx");
		message.setRecipient("yyy");
		message.setSubject("RestPwd");
		message.setBody("body");
		String status = emailSenderUtil.sendMail(message);
		Assert.assertEquals(status, "success");
	}


}
