package com.dojo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.dojo.exception.ConstraintException;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.repository.UserRepository;

@SpringBootTest(classes = {RegisterServiceTest.class})
public class RegisterServiceTest {

	@Mock
	UserRepository userRepo;
	
	@Mock
	SuccessResponse successResponse;
	
	@InjectMocks
	RegisterService regService;
	
	@Test
	void registerUser() throws ConstraintException {
//		given
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo","demo1234","test","test","test","test","test@test.com","test",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		given(userRepo.existsById(Mockito.anyString())).willReturn(false);
//		when
		SuccessResponse registerUser = regService.registerUser(user);
//		then
		assertNotNull(registerUser);
	}
	
	@Test
	void registerUserWithExistingUsername() throws ConstraintException {
//		given
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo","demo1234","test","test","test","test","test","test",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		given(userRepo.existsById(Mockito.anyString())).willReturn(true);
//		when registerUser() called & then
		assertThrows(ConstraintException.class, ()->regService.registerUser(user));
	}
	
	@Test
	void registerUserWithInvalidPasswordLength() throws ConstraintException {
//		given
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo","demo","test","test","test","test","test","test",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		given(userRepo.existsById(Mockito.anyString())).willReturn(false);
//		when registerUser() called & then
		assertThrows(ConstraintException.class, ()->regService.registerUser(user));
	}
	
	@Test
	void registerUserWithInvalidEmail() throws ConstraintException {
//		given
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo","demo1234","name","address","state","country","email","pan",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		given(userRepo.existsById(Mockito.anyString())).willReturn(false);
//		when registerUser() called & then
		assertThrows(ConstraintException.class, ()->regService.registerUser(user));
	}
	
	@Test
	void registerUserWithExistingPan() throws ConstraintException {
//		given
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo","demo1234","name","address","state","country","email","asdfg1234r",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		given(userRepo.existsById(Mockito.anyString())).willReturn(false);		
//		when registerUser() called & then
		assertThrows(ConstraintException.class, ()->regService.registerUser(user));
	}
		
}
