package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.UserDto;
import com.tw.entity.User;
import com.tw.enums.StatusType;

public class UserDtoConvertor implements Function<UserDto, User>{

	@Override
	public User apply(UserDto t) {
		
		User user = new User();
		user.setId(t.getId());
		user.setFirstName(t.getFirstName());
		user.setMiddleName(t.getMiddleName());
		user.setLastName(t.getLastName());
		user.setEmpPosition(t.getEmpPosition());
		user.setUserName(t.getUserName());
		user.setEmail(t.getEmail());
		user.setPassword(t.getPassword());
		user.setAddress(t.getAddress());
		user.setMobileNo(t.getMobileNo());
		user.setSalary(t.getSalary());
		user.setJoinDate(t.getJoinDate());
		user.setStatus(StatusType.Active.toString());
		user.setCreated(t.getCreated());
		user.setModified(t.getModified());
		return user;
	}

}
