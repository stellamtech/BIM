package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.CustomerDto;
import com.tw.dto.CustomerSpecDto;
import com.tw.service.CustomerService;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("save")
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerDto dto) {
		return customerService.saveCustomer(dto);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long id){
		return customerService.getCustomerById(id);
	}

	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
		return customerService.deleteCustomerById(id);
	}
	
	@PostMapping("list")
	public ResponseEntity<?> getCustomerList(@RequestBody CustomerSpecDto dto){
		return customerService.getCustomerList(dto);
	}
}
