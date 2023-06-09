package com.user.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.exceptions.UserCreationException;
import com.user.management.model.ResetUser;
import com.user.management.kafka.KafkaProducer;
import com.user.management.model.User;
import com.user.management.service.JwtService;
import com.user.management.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("auth/v1")
public class AuthorizationController {
	
	private final Logger LOGGER=LoggerFactory.getLogger(AuthorizationController.class);
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/authTest")
	public String authTest() {
		return "auth test is working";
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		boolean isUserCreated;
		try {
			isUserCreated=userService.createUser(user);
		} catch (UserCreationException e) {
			return new ResponseEntity<String>("Unable to create a user as there is another user with the same id", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		String username=user.getUsername();
		String password=user.getPassword();		
		
//		boolean isUserFound=userService.login(username, password);
		
		
//		if(isUserFound) {
//			System.out.println("entered the if block");
//			String token=Jwts.builder()
//							 .setSubject(username)
//							 .claim("roles", userService.getUserRole(username, password))
//							 .setIssuedAt(new Date())
//							 .setExpiration(new Date(System.currentTimeMillis()+300000))
//							 .signWith(SignatureAlgorithm.HS256, "fse1")
//							 .compact();
//			System.out.println("the value of token is "+token);
//			Map<String, String> map=new HashMap<String,String>();
//			map.put("jwt token", token);
//			map.put("message", "user successfully logged in");
//			map.put("role",userService.getUserRole(username, password));
//			return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
//		}
		
//		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		Authentication authentication=null;
		
		try {
			authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(Exception ex) {
			LOGGER.warn("username or password is not correct or something went wrong");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("username", username);
			map.put("loginStatus", false);
			map.put("message", "no user found with the provided username or else password didn't match");
			return new ResponseEntity<Map>(map, HttpStatus.CONFLICT);
		}
		
		if(authentication.isAuthenticated()) {
			LOGGER.info("user authenticated");
			
			sendMessage("user is authenticated");
			
			String role=userService.getUserRole(username, password);
			
			String token=jwtService.generateToken(username,role);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("username", username);
			map.put("token", token);
			map.put("role", role);
			map.put("loginStatus", true);
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		}
		else {
			LOGGER.warn("user is not authenticated");
			return new ResponseEntity<String>("no user found with the provided username or else password didn't match", HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("sendMessage/{message}")
	public String sendMessage(@PathVariable String message) {
		kafkaProducer.sendMessage(message);
		return message;
	}
	
	@PutMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetUser resetUser){
		boolean isUserIdIsOfSameUsername=userService.isUseridAndUseernameSame(resetUser.getUserId(), resetUser.getUsername());
		
		if(isUserIdIsOfSameUsername) {
			boolean isPasswordChanged=userService.changePassword(resetUser.getUserId(), resetUser.getNewPassword());
			if(isPasswordChanged) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}
				
		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
	
}
