package com.nvault.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.model.NVaultUser;
import com.nvault.service.UserService;

@RestController
public class UserRegisterController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<NVaultUser> createuser(@RequestBody NVaultUser user) throws Exception {
		if (userService.findById(user.getId()) != null) {
			return new ResponseEntity<NVaultUser>(HttpStatus.BAD_REQUEST);
		} else {
			String bucketName = user.getMail().split("@")[0];
			user.setBucketName(bucketName+user.getId());
			NVaultUser createdEmployee = userService.saveUser(user);
			if (createdEmployee != null) {
				return new ResponseEntity<NVaultUser>(createdEmployee, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<NVaultUser>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
