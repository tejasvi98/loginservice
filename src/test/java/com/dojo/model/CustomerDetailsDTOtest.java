package com.dojo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class CustomerDetailsDTOtest {
	@Autowired
	CustomerDetailsDTO customerDetailsDTO;
	@Test
	void setterTest() {
		customerDetailsDTO.setUsername("test");
		customerDetailsDTO.setPassword("test");
		customerDetailsDTO.setName("name");
		customerDetailsDTO.setAddress("address");
		customerDetailsDTO.setState("state");
		customerDetailsDTO.setCountry("country");
		customerDetailsDTO.setEmail("email");
		customerDetailsDTO.setPAN("pan");
		customerDetailsDTO.setContactNumber(0);
		customerDetailsDTO.setDOB(LocalDate.now());
		customerDetailsDTO.setAccountType("accountType");
		assertNotNull(customerDetailsDTO);
	}

}
