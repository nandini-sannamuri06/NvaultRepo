package com.nvault.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvault.model.NVaultUser;
@RestController
public class GatewayController {
	
	@RequestMapping("/user")
	public Map<String, Object> user() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     NVaultUser user = (NVaultUser)auth.getPrincipal();
		map.put("name", user.getUsername());
		map.put("userId", user.getId());
		map.put("roles", user.getAuthorities());
		return map;
	}

	@RequestMapping("/login")
	public String login() {
		return "forward:/";
	}
}
