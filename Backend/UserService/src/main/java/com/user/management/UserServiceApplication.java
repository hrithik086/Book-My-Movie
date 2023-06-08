package com.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.user.management.filter.JwtFilter;

@SpringBootApplication
public class UserServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		
	}

}
