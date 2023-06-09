package com.movie.management.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.movie.management.model.RegisterUserDto;
import com.movie.management.model.ResetUser;
import com.movie.management.model.UserDto;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/authentication")
@CrossOrigin()
public class ConsumerController {
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto){
		
//		String url="http://localhost:8080/auth/v1/login";
		String url="http://localhost:5000/auth/v1/login";
		
		RestTemplate rs=new RestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity he=new HttpEntity<UserDto>(userDto,headers);
		
		ResponseEntity<Map<String,Object>> result=null;

		
		try {
			result=rs.exchange(url, HttpMethod.POST, he, new ParameterizedTypeReference<Map<String, Object>>(){});
		}
		catch(Exception e) {
			Map<String, Object> map=new HashMap<>();
			map.put("message", "unable to login user");
			map.put("loginStatus", false);
			return new ResponseEntity<Map>(map,HttpStatus.CONFLICT);
		}
		
		
		return new ResponseEntity<Map<String,Object>>(result.getBody(), HttpStatus.OK);
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto user){
//		String url="http://localhost:8080/auth/v1/registerUser";
		String url="http://localhost:5000/auth/v1/registerUser";
		
		RestTemplate rs=new RestTemplate();
		HttpHeaders header=new HttpHeaders();
		header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity he=new HttpEntity<RegisterUserDto>(user,header);
		
		ResponseEntity<RegisterUserDto> response;
		
		try {
			response=rs.exchange(url, HttpMethod.POST, he, new ParameterizedTypeReference<RegisterUserDto>() {});
		}
		catch(Exception ex) {
			RegisterUserDto temp=new RegisterUserDto();
			temp.setRegisterStatus(false);
			return new ResponseEntity<RegisterUserDto>(temp,HttpStatus.CONFLICT);
		}
		
		response.getBody().setRegisterStatus(true);
		return new ResponseEntity<RegisterUserDto>(response.getBody(), HttpStatus.OK);
		
	}
	
	@PutMapping("/resetPassword")
	public ResponseEntity<?> changePassword(@RequestBody ResetUser resetUser){
//		String url="http://localhost:8080/auth/v1/resetPassword";
		String url="http://localhost:5000/auth/v1/resetPassword";
		
		RestTemplate restTemplate=new RestTemplate();
		HttpHeaders header=new HttpHeaders();
		header.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<ResetUser> he=new HttpEntity<ResetUser>(resetUser,header);
		
		ResponseEntity<Boolean> response;
		
		try {
			response=restTemplate.exchange(url, HttpMethod.PUT, he, new ParameterizedTypeReference<Boolean>() {
			});
			return new ResponseEntity<Boolean>(response.getBody(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
	}
	
}
