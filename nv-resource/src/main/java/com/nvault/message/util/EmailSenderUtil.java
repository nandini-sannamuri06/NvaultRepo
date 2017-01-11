package com.nvault.message.util;

import java.io.StringWriter;
import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nvault.message.model.Message;
import com.nvault.message.service.MessageService;
import com.nvault.model.NVaultUser;

@Component
public class EmailSenderUtil {

	@Autowired
	MessageService messageService;
	@Autowired
	public JavaMailSender mailSender;

	@Autowired
	public VelocityEngine velocityEngine;

	/**
	 * @param message
	 * Send Mail Asynchronously.
	 * @return
	 */
	@Async
	public String sendMail(Message message) {
		String status = "success";
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(message.getRecipient());
			messageHelper.setSubject(message.getSubject());
			VelocityContext context = new VelocityContext();
			context.put("user", message.getRecipient().split("@")[0]);
			context.put("message", message.getBody());
			velocityEngine.init();
			Template template = velocityEngine.getTemplate("ForgotPasswordTemplate.vm");
			StringWriter writter = new StringWriter();
			if (template != null)
				template.merge(context, writter);
			messageHelper.setText(writter.toString(), true);
			mailSender.send(mimeMessage);
			/*Need to insert Success Message in DB*/
		} catch (Exception e) {
			status = "failure";
			/*Need to insert Failure Message in DB*/
		}
		return status;
	}
	
	public String shareMail(HashMap<String, Object> shareMap) {
		String status = "success";
		try {
			MimeMessage shareMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(shareMessage);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    NVaultUser user = (NVaultUser)auth.getPrincipal();
			messageHelper.setTo(shareMap.get("mailId").toString());
			messageHelper.setSubject(shareMap.get("note").toString());
			VelocityContext context = new VelocityContext();
			context.put("user", "Nisum");
			context.put("message", "FileShare");
			System.out.println(shareMap.get("url"));
			context.put("url", shareMap.get("url").toString());
			context.put("username", user.getUsername());
			velocityEngine.init();
			Template template = velocityEngine.getTemplate("fileShare.vm");
			StringWriter writter = new StringWriter();
			if (template != null)
				template.merge(context, writter);
			messageHelper.setText(writter.toString(), true);
			mailSender.send(shareMessage);
		} catch (Exception e) {
			status = "failure";
		}
		return status;
	}

}
