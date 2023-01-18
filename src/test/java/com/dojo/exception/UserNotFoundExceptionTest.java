package com.dojo.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {

	@Test
	void test() {
		UserNotFoundException userNotFoundException = new UserNotFoundException("Test UnauthorizedException");
		assertEquals("Test UnauthorizedException", userNotFoundException.getMessage());
	}

}
