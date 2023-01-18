package com.dojo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.dojo.model.LoginDetail;
import com.dojo.service.UserServiceImpl;

@SpringBootTest(classes= {LoginControllerTest.class})
public class LoginControllerTest {
	
	@Mock
	UserServiceImpl userServiceImpl;
	
	@InjectMocks
	LoginController loginController;
	
	@Test
	void test_login() {
		LoginDetail user = new LoginDetail("test","test1234");
		loginController.login(user); 	
	}
	
	@Test
	void test_getValidity() {
		loginController.getValidity("test");
	}
	@Test
	void test_welcome() {
		loginController.welcome();
	}
}
