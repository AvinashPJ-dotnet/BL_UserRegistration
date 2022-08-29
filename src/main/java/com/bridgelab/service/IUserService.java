package com.bridgelab.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bridgelab.dto.LoginDTO;
import com.bridgelab.dto.ResetPasswordDTO;
import com.bridgelab.dto.ResponseDTO;
import com.bridgelab.model.User;

public interface IUserService {

	ResponseDTO registerUser(User user);
	
	ResponseDTO getAllUsers();
	
	ResponseDTO loginUser(LoginDTO user) ;
	
	ResponseDTO getUserById(int id);
	
	ResponseDTO updateUser(int id,User user);
	
	ResponseDTO deleteUser(int id);

	ResponseDTO verifyUser(int id);

	ResponseDTO forgotPassword(String email);
	
	ResponseDTO resetPassword(int id, ResetPasswordDTO resetPasswordDTO);
	
}
