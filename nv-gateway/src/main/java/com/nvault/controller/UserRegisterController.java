package com.nvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.model.User;
import com.nvault.service.UserService;

@RestController
public class UserRegisterController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> createuser(@RequestBody User user) throws Exception{
		if (userService.findById(user.getId()) != null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else {
			User createdEmployee = userService.saveUser(user);
			if (createdEmployee != null) {
				return new ResponseEntity<User>(createdEmployee, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
