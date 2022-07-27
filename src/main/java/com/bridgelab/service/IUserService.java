package com.bridgelab.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bridgelab.model.User;

public interface IUserService {

	User registerUser(User user);
	
	List<User> getAllUsers();
	
	ResponseEntity<String> loginUser(User user) ;
	
	ResponseEntity<User> updateUser(int id,User user);
	
	ResponseEntity<String> deleteUser(int id);
	
}
