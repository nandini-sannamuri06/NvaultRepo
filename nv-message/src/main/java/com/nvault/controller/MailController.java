package com.nvault.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	@RequestMapping("/test_method")
	public String test(){
		return "Success";
	}

	@RequestMapping(value = "/send_mail", method = RequestMethod.POST)
	public Mail sendMail(@RequestBody Mail mail) {
		System.out.println(mail.toString());
		return mail;
	}

}
