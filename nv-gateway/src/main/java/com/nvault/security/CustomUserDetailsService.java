package com.nvault.security;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nvault.model.NVaultUser;
import com.nvault.model.Role;
import com.nvault.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserService userService;
     
    public NVaultUser loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        NVaultUser user = userService.findByUserName(userName);
        if(user==null){
            throw new UsernameNotFoundException("Username not found"); 
        }
    	List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
        Authentication authToken = new UsernamePasswordAuthenticationToken(user,
				user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authToken);
            return user;
    }

}
