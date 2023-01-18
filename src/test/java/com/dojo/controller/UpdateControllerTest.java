package com.dojo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dojo.exception.InvalidTokenException;
import com.dojo.exception.UserNotFoundException;
import com.dojo.model.AuthResponse;
import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;
import com.dojo.service.UpdateService;
import com.dojo.service.UserServiceImpl;

@SpringBootTest(classes= {UpdateControllerTest.class})
public class UpdateControllerTest {

	@Mock
	 UpdateService updateService;
	
	@Mock
	UserServiceImpl userServiceImpl;
	
	@InjectMocks
	UpdateController UpdateController;
	
	@Test
	void test_updateDetails() {
		//given
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo","demo1234","test","test","test","test","test","test",1234567890,LocalDate.of(2020, 1, 8),"testAccount");
		//when
		SuccessResponse updateDetails = UpdateController.updateDetails(user);
		
	}
	
	@Test
	void findById() throws InvalidTokenException, UserNotFoundException {
//		given
		given(userServiceImpl.getValidity(anyString())).willReturn(new AuthResponse("test", true));
//		when & then
		assertNotNull(UpdateController.findById("token", "test"));
		
	}
	
}
