package com.bridgelab.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelab.dto.LoginDTO;
import com.bridgelab.dto.ResetPasswordDTO;
import com.bridgelab.dto.ResponseDTO;
import com.bridgelab.exceptions.UserException;
import com.bridgelab.jwtutils.TokenManager;
import com.bridgelab.model.User;
import com.bridgelab.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailSenderService mailService;

	@Value("${senderMailId}")
	private String SENDER_MAIL_ID;

	@Autowired
	private Environment environment;
	
//	@Autowired
//	ModelMapper modelMapper;
	
	@Autowired
	AmqpTemplate amqpTemplate;

	@Override
	public ResponseDTO registerUser(User user) {
		
//		User user = modelMapper.map(userDto, User.class);//use model mapper
		Optional<User> allUser = userRepository.findByEmail(user.getEmail());
		allUser.ifPresent(userObj -> {
			throw new UserException("User Already Present", HttpStatus.BAD_REQUEST);
		});
		User userData = userRepository.save(user);
		mailService.sendEmail(SENDER_MAIL_ID, userData.getEmail(),
				environment.getProperty("user.register.mail.subject"),
				environment.getProperty("user.register.mail.body") + userData.getId());
		
		amqpTemplate.convertAndSend("javainuseExchange", "javainuse", mailService);
		return new ResponseDTO(userData, "", 200);
	}

	@Override
	public ResponseDTO getAllUsers() {
		return new ResponseDTO(userRepository.findAll(),"success", 200);
	}

	@Override
	public ResponseDTO loginUser(LoginDTO user) {
		User userData = userRepository.findUserByUsername(user.getUsername());
		if (userData != null) {
			if (userData.getPassword().equals(user.getPassword())) {
//				mailService.sendEmail("pjavinash42@gmail.com", "avinash.pujari42@gmail.com", " test Spring mail sender",
//						"Test mail sender sent successfully");
				//used rabbitmq queue tool 
				amqpTemplate.convertAndSend("javainuseExchange", "javainuse", "avinash.pujari42@gmail.com");
				return new ResponseDTO(null, TokenManager.generateJwtToken(userData.getId()), 200);
			}
			throw new UserException("Incorrect Password", HttpStatus.NOT_FOUND);
		}
		throw new UserException("User name not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseDTO updateUser(int id, User user) {
		// TODO Auto-generated method stub
		userRepository.findById(id).orElseThrow(() -> {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		});
		userRepository.save(user);
		return new ResponseDTO(user, "Update successful",200);
	}

	@Override
	public ResponseDTO deleteUser(int id) {
		userRepository.findById(id).orElseThrow(() -> {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		});
		userRepository.deleteById(id);
		return new ResponseDTO(null,"Record Deleted Successfully", 200);
	}

	@Override
	public ResponseDTO getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseDTO(user.get(),"success", 200);
	}

	@Override
	public ResponseDTO verifyUser(int id) {
		Optional<User> user = userRepository.findById(id);
		user.orElseThrow(() -> {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		});
		user.get().setVerified(true);
		userRepository.save(user.get());
		return new ResponseDTO(null, "User Verified successfully", 200);
	}

	@Override
	public ResponseDTO forgotPassword(String email) {
		// TODO Auto-generated method stub
		Optional<User> allUser = userRepository.findByEmail(email);
		allUser.ifPresent(userObj -> {
			mailService.sendEmail(SENDER_MAIL_ID, userObj.getEmail(), "Reset the password",
					"To reset the password use this link in a postmen with desired fields "
							+ "http://localhost:8080/user/resetPassword?id=" + userObj.getId());

		});
		return new ResponseDTO(null, "Successfully sent a mail to reset your password", 200);
	}

	@Override
	public ResponseDTO resetPassword(int id, ResetPasswordDTO resetPasswordDTO) {
		// TODO Auto-generated method stub
		if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
			throw new UserException("Entered password mismatched, please check password", HttpStatus.NOT_ACCEPTABLE);
		}

		Optional<User> user = userRepository.findById(id);
		user.orElseThrow(() -> {
			throw new UserException("User not found", HttpStatus.NOT_FOUND);
		});
		user.get().setPassword(resetPasswordDTO.getNewPassword());
		userRepository.save(user.get());
		return new ResponseDTO(null, "User password reset successfully", 200);
	}
}
