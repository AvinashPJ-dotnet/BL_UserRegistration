package com.bridgelab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelab.dto.LoginDTO;
import com.bridgelab.dto.ResetPasswordDTO;
import com.bridgelab.dto.ResponseDTO;
import com.bridgelab.model.User;
import com.bridgelab.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	IUserService service;

	@PostMapping("/user")
	public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody User user) {
		return new ResponseEntity<>(service.registerUser(user), HttpStatus.OK);
	}

	@GetMapping("/user")
	@ApiOperation(value = "Get all users", notes = "Get all user from database")
	public ResponseEntity<ResponseDTO> getUsers() {
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseDTO> getUserById(@PathVariable("id") int id) {
		return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
	}

	@PutMapping("/user/login")
	public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO user) {
		return new ResponseEntity<>(service.loginUser(user), HttpStatus.OK);
	}
	

	@GetMapping("/verifyUser")
	public ResponseEntity<ResponseDTO> verifyUser(@RequestParam("id") int id) {
		return new ResponseEntity<>(service.verifyUser(id), HttpStatus.OK);
	}
	

	@PutMapping("/user/{id}")
	public ResponseEntity<ResponseDTO> updateUser(@RequestBody User user, @PathVariable("id") int id) {
		return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
		return new  ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
	}
	
	@PostMapping("/user/forgotPassword")
	public ResponseEntity<ResponseDTO> forgotPassword(@RequestParam("email") String email){
		return new ResponseEntity<>(service.forgotPassword(email), HttpStatus.OK);
	}
	
	@PostMapping("/user/resetPassword")
	public ResponseEntity<ResponseDTO> resetPassword(@RequestParam("id") int id,@RequestBody ResetPasswordDTO resetPasswordDTO){
		return new ResponseEntity<>(service.resetPassword(id,resetPasswordDTO), HttpStatus.OK);
	}

}
