package com.dojo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.dojo.exception.ConstraintException;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.service.RegisterService;
import com.dojo.service.RegisterServiceTest;

@SpringBootTest(classes = { RegisterServiceTest.class })
public class RegistrationControllerTest {

	@Mock
	RegisterService registerService;

	@InjectMocks
	RegistrationController registrationController;

	@Test
	void test_registerUser() throws ConstraintException {
		//given
		SuccessResponse successResponse = new SuccessResponse("testdone", HttpStatus.OK, LocalDate.of(2020, 1, 8));
		CustomerDetailsDTO user = new CustomerDetailsDTO("test", "test", "test", "test", "test", "test", "test", "test",
				1234567890, LocalDate.of(2020, 1, 8), "testAccount");
		given(registerService.registerUser(user)).willReturn(successResponse);
//		when
		SuccessResponse registerUser = registrationController.registerUser(user).getBody();
//		then
		assertNotNull(registerUser);
		
	}
	
	@Test
	void registerUserThrowsException() throws ConstraintException {
		//given
		CustomerDetailsDTO user = new CustomerDetailsDTO("test", "test", "test", "test", "test", "test", "test", "test",
				1234567890, LocalDate.of(2020, 1, 8), "testAccount");
		given(registerService.registerUser(user)).willThrow(ConstraintException.class);
//		when & then
		assertThrows(ConstraintException.class, ()->registrationController.registerUser(user));
	}
}
