package com.dojo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.dojo.exception.InvalidTokenException;
import com.dojo.exception.UnauthorizedException;
import com.dojo.exception.UserNotFoundException;
import com.dojo.jwt.JwtUtil;
import com.dojo.model.CustomerDetails;
import com.dojo.model.LoginDetail;
import com.dojo.repository.UserRepository;

@SpringBootTest(classes = { UserServiceImplTest.class })
public class UserServiceImplTest {

	@Mock
	UserRepository userRepo;

	@Mock
	JwtUtil jwUtil;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void test_loadUserByUsername() {
		CustomerDetails user = new CustomerDetails("test", "test", "test", "test", "test", "test", "test", "test",
				1234567890, LocalDate.of(2020, 1, 8), "testAccount");
		when(userRepo.findByUsername(Mockito.anyString())).thenReturn(user);
		userServiceImpl.loadUserByUsername("test");
	}

	@Test
	public void test_login() {
		CustomerDetails user = new CustomerDetails("demo", "demo1234", "test", "test", "test", "test", "test", "test",
				1234567890, LocalDate.of(2020, 1, 8), "testAccount");
//		UserToken userToken = new UserToken("test","test");
		when(userRepo.findByUsername(Mockito.anyString())).thenReturn(user);
		userServiceImpl.login(new LoginDetail(user.getUsername(), user.getPassword()));
	}

	@Test
	public void test_login1() {
		CustomerDetails user = new CustomerDetails("demo", "demo1234", "test", "test", "test", "test", "test", "test",
				1234567890, LocalDate.of(2020, 1, 8), "testAccount");
//		UserToken userToken = new UserToken("test","test");
		when(userRepo.findByUsername(Mockito.anyString())).thenReturn(user);
		userServiceImpl.login(new LoginDetail(user.getUsername(), user.getPassword()));
	}

	@Test
	public void test_getValidity() {
		when(jwUtil.validateToken(Mockito.anyString())).thenReturn(true);
		userServiceImpl.getValidity("testtesttestestststststststststssts");

	}

	@Test
	public void test_getUserByUsername() throws InvalidTokenException, UserNotFoundException {
		when(jwUtil.validateToken(Mockito.anyString())).thenReturn(true);
		when(jwUtil.extractUsername(Mockito.anyString())).thenReturn("name");
		CustomerDetails user = new CustomerDetails("test", "test", "test", "test", "test", "test", "test", "test",
				1234567890, LocalDate.of(2020, 1, 8), "testAccount");
		Optional<CustomerDetails> customer = Optional.of(user);
		when(userRepo.findById(Mockito.anyString())).thenReturn(customer);
		userServiceImpl.getUserByUsername("testtesttestestststststststststssts", "test");
	}

	@Test
	public void test_Loginfailure() {
		CustomerDetails model = new CustomerDetails("test", "test1234", "Rajesh", "Biharah123", "champaran", "India",
				"rajesh@rajesh.com", "amfsd1234g", 1234567890, LocalDate.now(), "saving");
		LoginDetail loginDetail = new LoginDetail("test", "test");
		when(userRepo.findByUsername(loginDetail.getUsername())).thenReturn(model);
		assertThrows(UnauthorizedException.class,()->userServiceImpl.login(loginDetail));
	}
}
