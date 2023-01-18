package com.dojo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingRequestHeaderException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;


@SpringBootTest(classes = {RestExceptionHandlerTest.class})
public class RestExceptionHandlerTest {

	@Test
	void test_handleUnauthorizedExceptions() {
		UnauthorizedException ex = new UnauthorizedException("Test_handleUnauthorizedException");
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
		restExceptionHandler.handleUnauthorizedExceptions(ex);
	}
	
	@Test
	void test_handleMissingRequestHeaderException() {
		MethodParameter methodParameter = null;
		MissingRequestHeaderException ex = new MissingRequestHeaderException("TestHeaderName",methodParameter);
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
		restExceptionHandler.handleMissingRequestHeaderException(ex);
	}
	
	@Test
	void test_handleExpiredJwtException() {
		Header header = null;
		Claims claims= null;
		String message = "test";
		ExpiredJwtException expiredJwtException = new ExpiredJwtException(header, claims, message);
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();	
		restExceptionHandler.handleExpiredJwtException(expiredJwtException);
	}
	
	@Test
	void test_constraintException() {
		String message = "test";
		ConstraintException constraintException = new ConstraintException(message);
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();	
		restExceptionHandler.handleConstraintException(constraintException);
	}
	
	@Test
	void test_userNotFoundException() {
		String message = "test";
		UserNotFoundException constraintException = new UserNotFoundException(message);
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();	
		restExceptionHandler.handleUserNotFound(constraintException);
	}
	
	
}
