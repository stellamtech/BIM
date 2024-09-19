package com.tw.dto;

import lombok.Data;

@Data
public class CustomerSpecDto {

	private String customerName;
	private String mobileNo;
	private Integer page;
	private Integer size;
}
