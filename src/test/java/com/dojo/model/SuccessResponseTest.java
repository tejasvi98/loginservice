package com.dojo.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
@SpringBootTest
class SuccessResponseTest {

	@Test
	void getterTest() {
		SuccessResponse successResponse = new SuccessResponse("success",HttpStatus.ACCEPTED,LocalDate.now());
		successResponse.getMessage();
		successResponse.getStatus();
		successResponse.getTimestamp();
	}
	
	@Test 
	void setterTest(){
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setMessage("string");
		successResponse.setStatus(HttpStatus.ACCEPTED);
		successResponse.setTimestamp(LocalDate.now());
	}

}
