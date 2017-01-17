package com.nvault.email;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.nvault.message.model.EMailSendStatus;
import com.nvault.message.model.Message;
import com.nvault.message.service.MessageService;


@Component
public class SmtpMailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	MessageService messageService;
	
	
	@Async
	public void send(String[] toAddress, String subject,String body, Message insertedMessage) throws Exception{
		
		MimeMessage message=javaMailSender.createMimeMessage();
		try {
			
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			
			helper.setSubject(subject);
			
			helper.setTo(toAddress[0]);
			
			if(toAddress.length>1){
			for(int i=1;i<toAddress.length;i++){
				helper.addCc(toAddress[i]);
			}
			}
			
			helper.setText(body,true);
			helper.setFrom(new InternetAddress("Sender Name" + "<" + "donotreply@netlokfamily.com" + ">"));


			javaMailSender.send(message);
			
			insertedMessage.setEmailSendStatus(EMailSendStatus.SENDSUCCESS);
			messageService.saveMessage(insertedMessage);
			
			
			
			
		} catch (Exception e) {

			insertedMessage.setEmailSendStatus(EMailSendStatus.SENDFAILED);
			messageService.saveMessage(insertedMessage);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
}
