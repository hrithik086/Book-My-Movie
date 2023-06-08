package com.movie.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.movie.management.filter.JwtFilter;

@SpringBootApplication
public class MovieServiceApplication {
	
	@Bean
	@CrossOrigin()
	public FilterRegistrationBean jwtFilterBean() {
		FilterRegistrationBean fb=new FilterRegistrationBean();
		fb.setFilter(new JwtFilter());
		fb.addUrlPatterns("/api/v1");
		return fb;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

}
