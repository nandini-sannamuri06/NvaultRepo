package com.nvault.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nvault.message.model.Message;
import com.nvault.message.repository.MessageRepository;

@Component
public class SmtpMailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MessageRepository messageRepository;
	
	public void send(String[] toAddress, String subject,String body) throws Exception{
		
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

			javaMailSender.send(message);
			
			Message msg=new Message();
			msg.setBody(body);
			msg.setSubject(subject);
			msg.setDate(new java.util.Date());
			
			
			messageRepository.saveAndFlush(msg);
			
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
}
