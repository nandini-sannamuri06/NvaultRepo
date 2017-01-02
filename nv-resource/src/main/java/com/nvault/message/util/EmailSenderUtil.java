package com.nvault.message.util;

import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.nvault.message.model.Message;

@Component
public class EmailSenderUtil {

	@Autowired
	public JavaMailSender mailSender;

	@Autowired
	public VelocityEngine velocityEngine;

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
		} catch (Exception e) {
			status = "failure";
		}
		return status;
	}

}
