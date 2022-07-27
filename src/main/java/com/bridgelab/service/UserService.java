package com.bridgelab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelab.exceptions.UserException;
import com.bridgelab.model.User;
import com.bridgelab.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailSenderService mailService;

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> allUser = userRepository.findByEmail(user.getEmail());
		allUser.ifPresent(userObj -> {
			throw new UserException("User Already Present", HttpStatus.BAD_REQUEST);
		});
		return userRepository.save(user);

	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<String> loginUser(User user) {
		User userData = userRepository.findUserByUsername(user.getUsername());
		if (userData != null) {
			if (userData.getPassword().equals(user.getPassword())) {
				mailService.sendEmail("pjavinash42@gmail.com", "avinash.pujari42@gmail.com", "test Spring mail sender",
						"Test mail sender sent successfully");
				return new ResponseEntity<>("User login successfully", HttpStatus.OK);
			}
			throw new UserException("Incorrect Password", HttpStatus.NOT_FOUND);
		}
		throw new UserException("User name not found", HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<User> updateUser(int id, User user) {
		// TODO Auto-generated method stub
		userRepository.findById(id).orElseThrow(() -> {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		});
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteUser(int id) {
		userRepository.findById(id).orElseThrow(() -> {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		});
		userRepository.deleteById(id);
		return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
	}
}
