package com.dojo.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class UserTokenTest {

	@Test
	void test() {
		UserToken userToken = new UserToken("user", "token");
		log.info(userToken.getUsername()+" "+userToken.getAuthToken());
	}

}
