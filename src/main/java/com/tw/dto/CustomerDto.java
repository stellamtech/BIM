package com.tw.dto;

import lombok.Data;

@Data
public class CustomerDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String customerName;

	private String mobileNo;

	private String remark;
	
	private String address;
}
