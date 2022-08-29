//package com.bridgelab.jwtutils;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.bridgelab.repository.UserRepository;
//
//@Component
//public class JwtUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
////		if(userRepository.findUserByUsername(username).getUsername().equals(username)) {
////			return new User(username, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
////		}
//		String dbUsername=userRepository.findByUsername(username).getUsername();
//		System.out.println("database ---"+dbUsername );
//		if (dbUsername.equals(username)) { 
//	         return new User(dbUsername, 
//	            "$2a$12$DXD7Q2RvZTVehINSFWIU..5XjmGjvHjZNNdPCl4V.Ouv0kOUaSwyi", 
//	            new ArrayList<>()); 
//	      }
//		else { 
//	         throw new UsernameNotFoundException("User not found with username: " + username); 
//	      } 
//	}
//
//}
