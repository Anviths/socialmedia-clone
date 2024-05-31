package com.jsp.SocialMedia.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.SocialMedia.UserService;
import com.jsp.SocialMedia.dto.ResponseStructure;
import com.jsp.SocialMedia.entity.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/facebook")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/user")
	@CrossOrigin
	public ResponseEntity<ResponseStructure<User>> name(@RequestParam("userData") String user,
			@RequestParam("profile") MultipartFile ptofile, @RequestParam("cover") MultipartFile cover) {
		// converting string into json
		try {
			User userj = mapper.readValue(user, User.class);

			return userService.saveUser(userj, ptofile, cover);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<ResponseStructure<User>>(HttpStatus.BAD_REQUEST);
		}

	}

	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(@RequestParam String userName, @RequestParam String password,
			@Autowired HttpSession session) {
		return userService.login(userName, password, session);
	}

	
	
	
	
	
	
	
	
	
	
	
}
