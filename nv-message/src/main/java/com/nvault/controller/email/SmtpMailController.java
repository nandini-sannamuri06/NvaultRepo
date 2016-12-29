package com.nvault.controller.email;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.controller.EmailToAddress;
import com.nvault.controller.Mail;
import com.nvault.email.SmtpMailSender;

@RestController
@RequestMapping("/mail")
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
	
	@RequestMapping("/test")
	public String test(){
		return "Success";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Mail sendMail(@RequestBody Mail mail) {
		
	try{
		
				System.out.println(mail.getToAddress().size());
				
				String[] toAddress = new String[mail.getToAddress().size()];
				
				int i=0;

				for (EmailToAddress address : mail.getToAddress()) {
					toAddress[i] = address.getText();
					//System.out.println(toAddress[i]);
					i++;
				}
				
				String subject =mail.getSubject();
				String body= mail.getBody();
				
				smtpMailSender.send(toAddress, subject, body);
					
				}catch(Exception ex){
					System.out.println(ex.getMessage()); 
				}
			
		/*System.out.println("------------");
		System.out.println(mail.toString());
		for (EmailToAddress address : mail.getToAddress()) {
			System.out.println(address.getText());
		}*/
		return mail;
	}
	
}
