package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CustomException;
import com.model.User;
import com.service.UserService;
import com.kafka.UserSignUpProducer;
@RestController
@Configuration
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserSignUpProducer userSignUpProducer;
	
	@GetMapping("/getusers")
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/getuser/{email}")
	public User getUser(@PathVariable String email) throws CustomException {
		User user;
		System.out.println("Came to user data service :"+email);
		try {
			user = userService.getUser(email);
		}catch(Exception e) {
			throw new CustomException("User not found with name : "+email);
		}
		return user;
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user) throws CustomException{
		try {
			userService.addUser(user);
			userSignUpProducer.sendSignInMessage(user);
		}catch(CustomException e) {
			throw new CustomException(e.getMessage());
		}
		
		return new ResponseEntity<>("User added successfully",HttpStatus.CREATED);
	}
	@PutMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestHeader("User") String email){
		userService.updateUser(email);
		return new ResponseEntity<>("Updated",HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/deleteuser")
	public ResponseEntity<?> deleteUser(@RequestHeader("User") String email){
		System.out.println(email);
		userService.deleteUser(email);
		return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
	}
}
