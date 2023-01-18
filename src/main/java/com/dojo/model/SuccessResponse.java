package com.dojo.model;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {
	private String message;
	private HttpStatus status;
	private LocalDate timestamp;
}
