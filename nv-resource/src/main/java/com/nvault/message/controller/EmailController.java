package com.nvault.message.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.message.model.Message;
import com.nvault.message.model.PasswordDetails;
import com.nvault.message.service.PwdDtlsService;
import com.nvault.message.util.EmailSenderUtil;

@RestController
public class EmailController {
	
	@Autowired
	public EmailSenderUtil emailSenderUtil;
	
	@Autowired
	
	public PwdDtlsService pwdDtlsService;
	
	
	@RequestMapping(value="/sendMail", method= RequestMethod.POST ,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> sendEmail(@RequestBody Message message){
		//Need tp insert the data into the pwd table with email and RandomId.
		PasswordDetails pwdDetails = new PasswordDetails();
		String uId = message.getBody().split("=")[1];
		pwdDetails.setMail(message.getRecipient());
		pwdDetails.setUniqueId(uId);
		pwdDetails.setExpired(0);
		pwdDtlsService.savePwdDtls(pwdDetails);
		//this should make it as asynchronous.
		emailSenderUtil.sendMail(message);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@RequestMapping(value="/shareMail", method= RequestMethod.POST ,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> shareMail(@RequestBody HashMap<String, Object> shareMap){
		
        String result = emailSenderUtil.shareMail(shareMap);
		if("success".equals(result)){
		return new ResponseEntity<String>(result,HttpStatus.OK);
		}else{
			return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}
	}

}
