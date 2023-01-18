package com.dojo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {MicroserviceExceptionTest.class})
public class MicroserviceExceptionTest {
	
	@Test
	public void test_MicroserviceException() {
		MicroserviceException microserviceException = new MicroserviceException("Test MicroService Exception");
	}

}
