package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.CustomerDto;
import com.tw.dto.CustomerSpecDto;

public interface CustomerService {

	ResponseEntity<?> saveCustomer(CustomerDto dto);

	ResponseEntity<?> getCustomerById(Long id);

	ResponseEntity<?> deleteCustomerById(Long id);

	ResponseEntity<?> getCustomerList(CustomerSpecDto dto);

}
