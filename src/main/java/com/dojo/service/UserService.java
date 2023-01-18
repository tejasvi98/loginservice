package com.dojo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dojo.exception.UnauthorizedException;
import com.dojo.model.AuthResponse;
import com.dojo.model.LoginDetail;
import com.dojo.model.UserToken;

public interface UserService extends UserDetailsService {

	/**
	 * authenticates the user
	 * @param customerDetails
	 * @return userToken
	 * @throws UnauthorizedException
	 */
	UserToken login(LoginDetail loginDetail);

	
	/**
	 * checks the validity of the JWT token
	 * @param token
	 * @return authResponse
	 */
	AuthResponse getValidity(String token);
}
