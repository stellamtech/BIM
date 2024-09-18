package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.CustomerDto;
import com.tw.entity.Customer;

public class CustomerCovertor implements Function<Customer,CustomerDto> {

	@Override
	public CustomerDto apply(Customer t) {
		
		CustomerDto dto=new CustomerDto();
		dto.setCustomerName(t.getCustomerName());
		dto.setMobileNo(t.getMobileNo());
		dto.setAddress(t.getAddress());
		dto.setId(t.getId());
		return dto;
	}

}
