package com.dojo.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {UnauthorizedExceptionTest.class})
public class UnauthorizedExceptionTest {
	
	@Test
	public void test_UnauthorizedException() {
		UnauthorizedException unauthorizedException = new UnauthorizedException("Test UnauthorizedException");
		assertEquals("Test UnauthorizedException", unauthorizedException.getMessage());
	}

}
