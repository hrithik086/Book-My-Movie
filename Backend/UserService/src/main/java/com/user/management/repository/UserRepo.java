package com.user.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	List<User> findByUsernameAndPassword(String username, String password);
	List<User> findByUsername(String username);
}
