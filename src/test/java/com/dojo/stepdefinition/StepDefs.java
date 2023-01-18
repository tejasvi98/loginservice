package com.dojo.stepdefinition;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.dojo.controller.LoginController;
import com.dojo.model.LoginDetail;
import com.dojo.model.UserToken;
import com.dojo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@AutoConfigureMockMvc
@CucumberContextConfiguration
@SpringBootTest
public class StepDefs {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private LoginController loginController;
	private LoginDetail loginDetail;
	private ResultActions action;
	private String customerDetails;

	@Autowired
	private UserRepository userRepository;

	private ResponseEntity<UserToken> token;

	@Given("^user enters the valid credential$")
	public void user_enters_valid_credential() {
		loginDetail = new LoginDetail("test", "test1234");
	}

	@Given("^user enters the invalid credential$")
	public void user_enters_invalid_credential() {
		loginDetail = new LoginDetail("test", "test");
	}

	@When("^performing login$")
	public void performing_login() throws Exception {
		action = mockMvc.perform(post("/login").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDetail)));

	}

	@Then("^login is successful$")
	public void login_is_successful() throws Exception {
		action.andExpect(status().isOk());
	}

	@Then("^error message displayed$")
	public void error_message_displayed() throws Exception {
		action.andExpect(status().isBadRequest());
	}

	@Given("^user provides token$")
	public void user_provides_token() {
		token = loginController.login(new LoginDetail("test", "test1234"));
	}

	@When("^performing validity$")
	public void performing_validity() throws Exception {
		action = mockMvc.perform(get("/validate").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).header("Authorization", token.getBody().getAuthToken()));
	}

	@Then("^validity status is shown$")
	public void validity_status_is_shown() throws Exception {
		action.andExpect(status().isOk());
	}

	@Given("^Customer provides (.+) details$")
	public void customer_provide_details(String detail) {
		if (detail.equals("empty"))
			customerDetails = "{\"name\":\"rajkumar\",\"address\":\"badarpur, delhi\",\"state\":\"Delhi\",\"country\":\"India\",\"email\":\"raj@gmail.com\",\"pan\":\"ASDFG1234T\",\"contactNumber\":1233445678,\"dob\":\"1996-01-21\",\"accountType\":\"accountType\"}";
		else
			customerDetails = "{\"username\":\"raj\",\"password\":\"rajk1234\",\"name\":\"rajkumar\",\"address\":\"badarpur, delhi\",\"state\":\"Delhi\",\"country\":\"India\",\"email\":\"raj@gmail.com\",\"pan\":\"ASDFG1234T\",\"contactNumber\":1233445678,\"dob\":\"1996-01-21\",\"accountType\":\"accountType\"}";
	}

	@When("^Customer submit the details$")
	public void customer_submit_the_details() throws Exception {
		action = mockMvc.perform(post("/registerUser").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(customerDetails));
	}

	@Then("^Registration (.+)$")
	public void registration(String status) throws Exception {
		action.andExpect(status.equals("successful") ? status().isOk() : status().isBadRequest());
		if (status.equalsIgnoreCase("failure") && userRepository.existsById("raj"))
			userRepository.deleteById("raj");
	}

	@Given("^Customer provide invalid (.+)$")
	public void customer_provide_invalid(String field) {
		if (field.equalsIgnoreCase("password"))
			customerDetails = "{\"username\":\"raj\",\"password\":\"raj\",\"name\":\"rajkumar\",\"address\":\"badarpur, delhi\",\"state\":\"Delhi\",\"country\":\"India\",\"email\":\"raj@gmail.com\",\"pan\":\"ASDFG1234T\",\"contactNumber\":1233445678,\"dob\":\"1996-01-21\",\"accountType\":\"accountType\"}";
		else if (field.equalsIgnoreCase("pan"))
			customerDetails = "{\"username\":\"raj\",\"password\":\"raj12345\",\"name\":\"rajkumar\",\"address\":\"badarpur, delhi\",\"state\":\"Delhi\",\"country\":\"India\",\"email\":\"raj@gmail.com\",\"pan\":\"amfsd1234g\",\"contactNumber\":1233445678,\"dob\":\"1996-01-21\",\"accountType\":\"accountType\"}";
		else
			customerDetails = "{\"username\":\"raj\",\"password\":\"raj12345\",\"name\":\"rajkumar\",\"address\":\"badarpur, delhi\",\"state\":\"Delhi\",\"country\":\"India\",\"email\":\"rajxgmail.com\",\"pan\":\"ASDFG1234T\",\"contactNumber\":1233445678,\"dob\":\"1996-01-21\",\"accountType\":\"accountType\"}";

	}

	@Then("^Error message with bad request shown$")
	public void invalid_message_will_be_shown() throws Exception {
		action.andExpect(status().isBadRequest());
	}

	public static String asJsonString(LoginDetail login) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(login);
	}

}
