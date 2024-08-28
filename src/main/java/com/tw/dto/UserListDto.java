package com.tw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDto {
	
	private Long id;
	private String userName; 
	private String email;
	private String mobileNo;
	
}
