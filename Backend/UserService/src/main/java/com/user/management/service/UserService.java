package com.user.management.service;

import java.util.List;

import com.user.management.exceptions.UserCreationException;
import com.user.management.model.User;

public interface UserService {
	List<User> getAllUsers();
	boolean createUser(User user) throws UserCreationException;
	boolean login(String username, String password);
	boolean getUserByUsername(String username);
	String getUserRole(String username, String password);
	boolean isUseridAndUseernameSame(int userId, String username);
	boolean changePassword(int userId, String password);
}
