package com.dojo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dojo.model.CustomerDetails;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.repository.UserRepository;

@Service
public class UpdateServiceImpl implements UpdateService {

	@Autowired
	UserRepository userRepo;

	@Override
	public SuccessResponse updateUser(CustomerDetailsDTO customer) {
		CustomerDetails customerDetails = convertCustomerDetailsDTOtoEntity(customer);
		
		return new SuccessResponse(
				userRepo.save(customerDetails) instanceof CustomerDetails ? "customer update successfully" : "update failed",
				HttpStatus.OK, LocalDate.now());
	}
	
	private CustomerDetails convertCustomerDetailsDTOtoEntity(CustomerDetailsDTO customer) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setUsername(customer.getUsername());
		customerDetails.setPassword(customer.getPassword());
		customerDetails.setName(customer.getName());
		customerDetails.setAddress(customer.getAddress());
		customerDetails.setState(customer.getState());
		customerDetails.setCountry(customer.getCountry());
		customerDetails.setEmail(customer.getEmail());
		customerDetails.setPAN(customer.getPAN());
		customerDetails.setContactNumber(customer.getContactNumber());
		customerDetails.setDOB(customer.getDOB());
		customerDetails.setAccountType(customer.getAccountType());
		return customerDetails;
	}

}
