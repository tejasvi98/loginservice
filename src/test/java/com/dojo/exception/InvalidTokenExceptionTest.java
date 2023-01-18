package com.dojo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {InvalidTokenExceptionTest.class})
public class InvalidTokenExceptionTest {
	
	@Test
	public void test_InvalidTokenException() {
		InvalidTokenException invalidTokenException = new InvalidTokenException("test Invalid User Exception");
	}

}
