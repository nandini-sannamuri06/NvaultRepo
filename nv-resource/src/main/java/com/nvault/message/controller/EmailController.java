package com.nvault.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.message.model.Message;
import com.nvault.message.util.EmailSenderUtil;

@RestController
public class EmailController {
	
	@Autowired
	public EmailSenderUtil emailSenderUtil;
	
	@RequestMapping(value="/sendMail", method= RequestMethod.POST ,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> sendEmail(@RequestBody Message message){
		String result = emailSenderUtil.sendMail(message);
		if("success".equals(result)){
		return new ResponseEntity<String>(result,HttpStatus.OK);
		}else{
			return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		}
	}

}
