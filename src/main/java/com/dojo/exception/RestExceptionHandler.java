package com.dojo.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dojo.model.MessageResponse;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

//global exception handler
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * to handle unauthorized exception
	 * @param UnauthorizedException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ UnauthorizedException.class,InvalidTokenException.class})
	public ResponseEntity<?> handleUnauthorizedExceptions(Exception ex) {
		log.error("Unauthorized request...");
		return ResponseEntity.badRequest()
				.body(new MessageResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED));
	}

	
	/**
	 * handles exception when authorization token is missing
	 * @param MissingRequestHeaderException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<MessageResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		log.error("Required Bearer token....");
		return ResponseEntity.badRequest().body(new MessageResponse("Required token", HttpStatus.BAD_REQUEST));
	}

	
	/**Checks header of the request that the user enters.
	 * handles expires JWT token
	 * @param ExpiredJwtExceptionException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<MessageResponse> handleExpiredJwtException(ExpiredJwtException ex) {

		log.error("Token has expired");
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<MessageResponse> handleConstraintException(ConstraintException ex) {

		log.error("failed constraint");
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MessageResponse> handleUserNotFound(Exception ex) {

		log.error("User Not Found");
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}
}
