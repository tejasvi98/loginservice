package com.dojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.exception.ConstraintException;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
	
	@Autowired
	RegisterService registerService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<SuccessResponse> registerUser(@RequestBody CustomerDetailsDTO user)
			throws ConstraintException{
		log.debug("inside registerUser method of offer microservice");
		return ResponseEntity.ok(registerService.registerUser(user));
		
	}
	

}
