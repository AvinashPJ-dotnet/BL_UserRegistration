package com.bridgelab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.model.User;
import com.bridgelab.service.IUserService;

@RestController
public class UserController {

	@Autowired
	IUserService service;

	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		System.out.println("User object from postman " + user.toString());
		return service.registerUser(user);
	}

	@GetMapping("/user")
	public List<User> getUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		return service.loginUser(user);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser( @RequestBody User user ,@PathVariable("id") int id) {
		return service.updateUser(id,user);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		return service.deleteUser(id);
	}

}
