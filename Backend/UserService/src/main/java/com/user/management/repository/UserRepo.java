package com.user.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.management.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
//	List<User> findByUsernameAndPassword(String username, String password);
	List<User> findByUsername(String username);
	
	@Query(value="SELECT * FROM user as u WHERE u.username= :username AND u.password= :password", nativeQuery = true)
	List<User> searchUsingUsernameAndPassword(String username, String password);
}
