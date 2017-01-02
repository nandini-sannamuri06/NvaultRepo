package com.nvault.message.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.nvault.message.model.Message;


import com.nvault.message.service.MessageService;
import com.nvault.model.NVaultUser;


@RestController
@RequestMapping("/mail")
public class SmtpMailController {
	
	@Autowired
	com.nvault.email.SmtpMailSender smtpMailSender;
	
	@Autowired
	MessageService messageService;
	
	@Value("${spring.mail.username}")
	String sender;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public com.nvault.controller.Mail sendMail(@RequestBody com.nvault.controller.Mail mail) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    NVaultUser user = (NVaultUser)auth.getPrincipal();
 		
		

		try {
			String[] toAddress = new String[mail.getToAddress().size()];
			int i = 0;
			for (com.nvault.controller.EmailToAddress address : mail.getToAddress()) {
				toAddress[i] = address.getText();
				i++;
			}
			String subject = mail.getSubject();
			String body = mail.getBody();

			smtpMailSender.send(toAddress, subject, body);
			
			
			
			
			Message message=new Message();
			
			message.setBody(mail.getBody());
			message.setSubject(mail.getSubject());

			message.setRecipient( "mallik@nisum.com,siva@nisum.com" );
			message.setId(user.getId());
			
			message.setSender(sender);
			
			
			// to save mail in db
			messageService.saveMessage(message);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return mail;
	}
	
}
