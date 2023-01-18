package com.dojo;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dojo.model.CustomerDetails;
import com.dojo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableWebMvc
public class LoginmicroserviceApplication {

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(LoginmicroserviceApplication.class, args);
	}

	@PostConstruct
	public void demoUsers() {
		log.debug("Adding demo user in database using post construct");
		CustomerDetails model = new CustomerDetails("test", "test1234", "Rajesh", "Biharah123", "champaran", "India",
				"rajesh@rajesh.com", "amfsd1234g", 1234567890, LocalDate.now(), "saving");
		userRepo.save(model);
		log.debug("user saved in database");
	}

}
