package com.dojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.exception.UnauthorizedException;
import com.dojo.model.AuthResponse;
import com.dojo.model.LoginDetail;
import com.dojo.model.UserToken;
import com.dojo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	@Autowired(required = true)
	UserService userService;

	/**
	 * authenticates the user
	 * 
	 * @param userModel
	 * @return userToken
	 * @throws UnauthorizedException
	 */
	@PostMapping("/login")
	public ResponseEntity<UserToken> login(@RequestBody LoginDetail user) {
		log.info("Authentication Started");
		return ResponseEntity.ok(userService.login(user));
	}

	/**
	 * checks for the validity of the JWT Token
	 * 
	 * @param token
	 * @return authResponse
	 */
	@GetMapping("/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
		log.info("Inside Token Validation... ");
		return ResponseEntity.ok(userService.getValidity(token));
	}

}