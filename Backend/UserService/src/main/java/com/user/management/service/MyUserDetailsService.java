package com.user.management.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.user.management.model.User;
import com.user.management.repository.UserRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {
	
	private final Logger LOGGER=LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> userList=userRepo.findByUsername(username);
		LOGGER.info("data fetched from database");
		if(userList==null || userList.size()<=0) {
			LOGGER.warn("username not found in the database");
			throw new UsernameNotFoundException("no user found with that username");
		}
		else {
			User user=userList.get(0);
			LOGGER.info("user found in the database");
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority(user.getRole()))));
		}
	}

}