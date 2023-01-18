package com.dojo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class CustomerDetailsTest {

	@Test
	void getterTest() {
		CustomerDetails user = new CustomerDetails("demo","demo1234","name","address","state","country","email","asdfg1234r",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		assertNotNull("CustomerDetails [username=" + user.getUsername() + ", password=" + user.getPassword() + ", name=" + user.getName() + ", address="
				+ user.getAddress() + ", state=" + user.getState() + ", country=" + user.getCountry() + ", email=" + user.getEmail() + ", PAN=" + user.getPAN()
				+ ", contactNumber=" + user.getContactNumber() + ", DOB=" + user.getDOB() + ", accountType=" + user.getAccountType() + "]");
	}
	
	@Test
	void setterTest() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setUsername("user");
		customerDetails.setPassword("test");
		customerDetails.setName("test");
		customerDetails.setAddress("test");
		customerDetails.setCountry("test");
		customerDetails.setState("test");
		customerDetails.setEmail("test");
		customerDetails.setPAN("test");
		customerDetails.setContactNumber(1234567890);
		customerDetails.setDOB(LocalDate.now());
		customerDetails.setAccountType("test");
		log.info(customerDetails.toString()); 
	}
}
