package com.nvault.security;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nvault.model.Role;
import com.nvault.model.User;
import com.nvault.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserService userService;
     
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userService.findByUserName(userName);
        if(user==null){
            throw new UsernameNotFoundException("Username not found"); 
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (Role r : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(r.getCode()));
		}
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
                 user.isAccountNonExpired(), true, true, true, grantedAuthorities);
    }

}
