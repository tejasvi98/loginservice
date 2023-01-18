package com.dojo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerDetailsDTO {

	private String username;
	private String password;
    private String name;
    private String address;
    private String state;
    private String country;
	@Email(message = "email is not valid")
    private String email;
    private String PAN;
	@Column(length = 10)
    private long contactNumber;
	@NotNull
    private LocalDate DOB;
    private String accountType;
	
	
}
