package com.dojo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import com.dojo.model.AuthResponse;
import com.dojo.model.LoginDetail;
import com.dojo.model.UserToken;
import com.dojo.repository.UserRepository;
import com.dojo.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	LoginController loginController;

	@MockBean
	UserServiceImpl userServiceImpl;
	@Autowired
	LoginDetail loginDetail;

	@Autowired
	UserRepository userRepository;
	
	@Test
	void loginUnitTest() throws Exception {
		
		//given
		loginDetail = new LoginDetail("username","username");
		UserToken userToken = new UserToken("username","userToken");
		given(userServiceImpl.login(loginDetail)).willReturn(userToken);
		//when
		RequestBuilder content = post("/login").accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDetail));
		//then
		mockMvc.perform(content).andExpect(status().isOk());
	}

	@Test
	void loginSuccessForCorrectCredential() throws Exception {
		//given
		LoginDetail loginDetail = new LoginDetail("test", "test1234");
		//when
		ResultActions actions = mockMvc.perform(post("/login").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDetail)));
		//then
		actions.andExpect(status().isOk());
	}
	@Test
	void loginFailureForBadCredential() throws Exception {
//		given
		LoginDetail loginDetail = new LoginDetail("test", "wrongpassword");
		//when
		ResultActions actions = mockMvc.perform(post("/login").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDetail)));
		//then
		actions.andExpect(status().isOk());
	}

	@Test
	void checkForValidUser() throws Exception {
//		given
		AuthResponse authResponse = new AuthResponse("test",true);
		given(userServiceImpl.getValidity("token")).willReturn(authResponse);
//		when
		ResultActions actions = mockMvc
				.perform(get("/validate").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "token"));
//		then
		actions.andExpect(status().isOk());
	}

	public static String asJsonString(LoginDetail login) throws JsonProcessingException {
			return new ObjectMapper().writeValueAsString(login);
	}
}
