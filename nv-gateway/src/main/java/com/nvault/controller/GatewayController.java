package com.nvault.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.model.NVaultUser;
import com.nvault.model.PasswordDetails;
import com.nvault.service.PwdDtlsService;
import com.nvault.service.UserService;
import com.nvault.util.ResetPassword;

@RestController
public class GatewayController {

	@Autowired
	public UserService userService;
	@Autowired
	public PwdDtlsService pwdDtlsService;

	@RequestMapping("/user")
	public Map<String, Object> user() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NVaultUser user = (NVaultUser) auth.getPrincipal();
		map.put("name", user.getUsername());
		map.put("userId", user.getId());
		map.put("roles", user.getAuthorities());
		return map;
	}

	@RequestMapping("/login")
	public String login() {
		return "forward:/";
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getEmailDtls(@RequestParam(value = "email") String email) {
		NVaultUser user = userService.findByEmailID(email);
		if (user == null) {

			return new ResponseEntity<String>("Email Id is not Exists in DB", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Email Id is available", HttpStatus.OK);
	}

	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResetPassword> resetPassword(@RequestBody ResetPassword resetPassword) throws Exception {
		PasswordDetails dtls = pwdDtlsService.getPwdDtls(resetPassword.getId());
		if (dtls!=null && dtls.getExpired()!=1) {
			NVaultUser user = userService.updatePassword(resetPassword.getPassword(), dtls.getMail());
			if (user != null) {
				dtls.setExpired(1);
				pwdDtlsService.savePwdDtls(dtls);
				resetPassword.setSuccess("success");
				return new ResponseEntity<ResetPassword>(resetPassword, HttpStatus.OK);
			} else {
				resetPassword.setSuccess("faiure");
				return new ResponseEntity<ResetPassword>(resetPassword, HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<ResetPassword>(resetPassword, HttpStatus.CONFLICT);
		}
	}
}
