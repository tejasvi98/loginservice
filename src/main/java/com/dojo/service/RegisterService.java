package com.dojo.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dojo.exception.ConstraintException;
import com.dojo.model.CustomerDetails;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisterService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	SuccessResponse successResponse;

	/**
	 * For Registering new users
	 * 
	 * @param offer
	 * @return {@link SuccessResponse}
	 * @throws ConstraintException 
	 */
	public SuccessResponse registerUser(CustomerDetailsDTO user) throws ConstraintException {
		if (user != null && !userRepo.existsById(user.getUsername())) {
			log.info("User registration started");
			if(passwordLengthValidation(user)) throw new ConstraintException("password length less than 8");
			if(panValidation(user.getPAN())) throw new ConstraintException("PAN already exist");
			if(!emailValidation(user.getEmail())) throw new ConstraintException("Email validation failed");
			CustomerDetails customer = convertCustomerDetailsDTOtoEntity(user);
			userRepo.save(customer);
			log.info("User Registered Successfully");
			return onSuccessResponse();
		}
		log.info("User Registration failed");
		throw new ConstraintException("username already exist or no data passed");
	}
	
	private CustomerDetails convertCustomerDetailsDTOtoEntity(CustomerDetailsDTO customerDetailsDTO) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setUsername(customerDetailsDTO.getUsername());
		customerDetails.setPassword(customerDetailsDTO.getPassword());
		customerDetails.setName(customerDetailsDTO.getName());
		customerDetails.setAddress(customerDetailsDTO.getAddress());
		customerDetails.setState(customerDetailsDTO.getState());
		customerDetails.setCountry(customerDetailsDTO.getCountry());
		customerDetails.setEmail(customerDetailsDTO.getEmail());
		customerDetails.setPAN(customerDetailsDTO.getPAN());
		customerDetails.setContactNumber(customerDetailsDTO.getContactNumber());
		customerDetails.setDOB(customerDetailsDTO.getDOB());
		customerDetails.setAccountType(customerDetailsDTO.getAccountType());
		return customerDetails;
	}

	private SuccessResponse onSuccessResponse() {
		successResponse.setMessage("Registration successful");
		successResponse.setStatus(HttpStatus.OK);
		successResponse.setTimestamp(LocalDate.now());
		return successResponse;
	}
	
	public boolean passwordLengthValidation(CustomerDetailsDTO user) {
		return user.getPassword().length()<8;
	}
	
	private boolean emailValidation(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private boolean panValidation(String pan) {
		Optional<CustomerDetails> details = userRepo.findByPAN(pan);
		return details.isPresent();
	}
}
