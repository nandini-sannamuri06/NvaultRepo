package com.nvault.controller.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	/*@RequestMapping(value="/sendmail", method=RequestMethod.POST)
	public String sendMail(@RequestBody String body){
		
		
		System.out.println(body);

		JSONObject jsonObj=null;
		try {
			jsonObj = new JSONObject(body);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	}*/
	
	
	@RequestMapping(value="/sendmail", method=RequestMethod.POST)
	public String sendMail(@RequestBody UserEmail userEmail){
System.out.println(userEmail);
		
		 
		
		try{
			
			String[] toAddress = userEmail.getToAddress();
			String subject =userEmail.getSubject();
			String body= userEmail.getBody();
			
			smtpMailSender.send(toAddress, subject, body);
				
			}catch(Exception ex){
				return ex.getMessage();
			}
		
		System.out.println(userEmail);
		return "true";
	}
	
}
