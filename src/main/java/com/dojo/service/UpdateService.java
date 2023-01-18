package com.dojo.service;

import com.dojo.model.CustomerDetailsDTO;
import com.dojo.model.SuccessResponse;

@FunctionalInterface
public interface UpdateService {
	public SuccessResponse updateUser(CustomerDetailsDTO customer);
}
