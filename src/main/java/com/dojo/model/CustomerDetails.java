package com.dojo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name="user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetails {

	@Id
	private String username;
	@NotBlank
	private String password;
	@NotBlank
    private String name;
	@NotBlank
    private String address;
	@NotBlank
    private String state;
	@NotBlank
    private String country;
	@NotBlank
	@Email(message = "email is not valid")
    private String email;
	@NotBlank
    private String PAN;
	@Column(length = 10)
    private long contactNumber;
	@NotNull
    private LocalDate DOB;
	@NotBlank
    private String accountType;
	
	
}
