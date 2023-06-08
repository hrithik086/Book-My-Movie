package com.user.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.model.User;
import com.user.management.service.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/userTest")
	public String userTest() {
		return "userTest is working";
	}
	
	
	@GetMapping("getAllUsers")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
}
