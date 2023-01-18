package com.dojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.exception.InvalidTokenException;
import com.dojo.exception.UserNotFoundException;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.repository.UserRepository;
import com.dojo.service.UpdateService;
import com.dojo.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UpdateController {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UpdateService updateService;
	@PutMapping("updateDetails")
	public SuccessResponse updateDetails(@RequestBody CustomerDetailsDTO customer){
		log.info("update started");
		log.info("update completed");
		return updateService.updateUser(customer);
		
	}
	@PostMapping("/finduser/{username}")
	public ResponseEntity<CustomerDetailsDTO> findById(@RequestHeader("Authorization") String token,@PathVariable String username) throws InvalidTokenException, UserNotFoundException{
	log.debug("inside findbyId method");
		return new ResponseEntity<CustomerDetailsDTO>( userServiceImpl.getUserByUsername(token,username),HttpStatus.OK);
	}
	
}
