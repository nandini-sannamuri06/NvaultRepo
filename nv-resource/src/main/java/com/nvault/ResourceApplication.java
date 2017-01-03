package com.nvault;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SpringBootApplication
public class ResourceApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		@Override
		  public void configure(WebSecurity web) throws Exception {
		    web
		      .ignoring()
		         .antMatchers("/resource/**"); // #3
		  }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.httpBasic().and()
				.authorizeRequests()
					.anyRequest().permitAll();
			http
			  .csrf().disable();
		}
	}
}
