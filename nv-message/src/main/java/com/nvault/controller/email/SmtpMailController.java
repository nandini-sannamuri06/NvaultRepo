package com.nvault.controller.email;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.email.SmtpMailSender;

@RestController
public class SmtpMailController {
	
	@Autowired
	SmtpMailSender smtpMailSender;
	
	//@RequestMapping(value="/sendmail", method=RequestMethod.POST)
	//public String sendMail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body ) {
		
		//try{
		//smtpMailSender.send(to, subject, body);
		
		//}catch(Exception ex){
			//return ex.getMessage();
		//}
		//return "success";
	//}

	@RequestMapping(value="/sendmail", method=RequestMethod.POST)
	public String sendMail(@RequestBody String body){
		
		
		System.out.println(body);
		
		 //JSONParser parser = new JSONParser(); JSONObject json = (JSONObject) parser.parse(stringToParse);
		JSONObject jsonObj = new JSONObject(body);
		
		try{
			
			String to = jsonObj.getString("to");
			String subject =jsonObj.getString("subject");
			String text=jsonObj.getString("body");
			
			smtpMailSender.send(to, subject, text);
				
			}catch(Exception ex){
				return ex.getMessage();
			}
		
		System.out.println(body);
		return body;
	}
	
}
