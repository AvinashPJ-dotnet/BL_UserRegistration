package com.bridgelab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelab.model.User;
import com.bridgelab.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("calling service method"+user.toString());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User loginUser(User user) {
		User userObj=userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		return userObj;
	}

}
