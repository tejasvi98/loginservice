package com.dojo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dojo.model.CustomerDetails;
@Repository
public interface UserRepository extends JpaRepository<CustomerDetails, String>{
	
	//to find a user by its user name
	public CustomerDetails findByUsername(String username);
	
	public Optional<CustomerDetails> findByPAN(String pan);
}

