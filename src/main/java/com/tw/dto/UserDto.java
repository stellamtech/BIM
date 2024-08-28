package com.tw.dto;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String empPosition;
	private String userName;
	private String email;
	private String password;
	private String address;
	private String mobileNo;
	private double salary;
	private Date joinDate;
	private String status;
	private String[] roles;
	private Calendar created;
	private Calendar modified;
	
}
