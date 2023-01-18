package com.dojo.service;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dojo.exception.InvalidTokenException;
import com.dojo.exception.UnauthorizedException;
import com.dojo.exception.UserNotFoundException;
import com.dojo.jwt.JwtUtil;
import com.dojo.model.AuthResponse;
import com.dojo.model.CustomerDetails;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.LoginDetail;
import com.dojo.model.UserToken;
import com.dojo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String userName) {
//	to find the details and creating User
		log.info("Inside loadbyusername");
		CustomerDetails user = userRepository.findByUsername(userName);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	// for authentication of user
	@Override
	public UserToken login(LoginDetail loginDetail) {

		final UserDetails userdetails = loadUserByUsername(loginDetail.getUsername());

		UserToken userToken = new UserToken();

		// if the password matches
		if (userdetails.getPassword().equals(loginDetail.getPassword())) {
			log.info("Customer Authenticated");
			log.debug("Generating Token");
			// set the values for the token
			userToken.setUsername(loginDetail.getUsername());
			userToken.setAuthToken(jwtUtil.generateToken(userdetails));

			return userToken;
		} else {
			log.error("authentication failed");
			throw new UnauthorizedException("Invalid username or password");
		}
	}

	// validates the JWT token
	@Override
	public AuthResponse getValidity(String token) {
		AuthResponse authResponse = new AuthResponse();
		// if valid
		if (jwtUtil.validateToken(token)) {
			log.info("Token is valid");

			// extract the user name
			String username = jwtUtil.extractUsername(token);

			// set the values for the response
			authResponse.setUsername(username);
			authResponse.setValid(true);
			return authResponse;
		} else {
			log.error("Token is invalid or expired...");
			throw new InvalidTokenException("Token is invalid or expired");
		}

	}

	public CustomerDetailsDTO getUserByUsername(String token,String username) throws InvalidTokenException, UserNotFoundException {
		Optional<CustomerDetails> customerDetails = userRepository.findById(username);
		if(!getValidity(token).isValid()) throw new InvalidTokenException("Token Invalid");
		log.debug("fetching user from database");
		if(customerDetails.isPresent()) {
			return convertCustomerDetailsToDTO(customerDetails.get());
		}else throw new UserNotFoundException("User Not Found");		
	}
	
	private CustomerDetailsDTO convertCustomerDetailsToDTO(CustomerDetails user) {
		CustomerDetailsDTO customerDetails = new CustomerDetailsDTO();
		customerDetails.setUsername(user.getUsername());
		customerDetails.setPassword(user.getPassword());
		customerDetails.setName(user.getName());
		customerDetails.setAddress(user.getAddress());
		customerDetails.setState(user.getState());
		customerDetails.setCountry(user.getCountry());
		customerDetails.setEmail(user.getEmail());
		customerDetails.setPAN(user.getPAN());
		customerDetails.setContactNumber(user.getContactNumber());
		customerDetails.setDOB(user.getDOB());
		customerDetails.setAccountType(user.getAccountType());
		return customerDetails;
	}
}
