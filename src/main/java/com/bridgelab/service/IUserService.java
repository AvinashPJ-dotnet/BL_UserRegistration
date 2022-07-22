package com.bridgelab.service;

import java.util.List;

import com.bridgelab.model.User;

public interface IUserService {

	User registerUser(User user);
	
	List<User> getAllUsers();
	
	User loginUser(User user);
}
