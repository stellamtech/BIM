package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.UserDto;
import com.tw.entity.User;
import com.tw.enums.StatusType;

public class UserListConvertor implements Function<User, UserDto>{

	@Override
	public UserDto apply(User t) {
		UserDto dto = new UserDto();
		dto.setId(t.getId());
		dto.setFirstName(t.getFirstName());
		dto.setMiddleName(t.getMiddleName());
		dto.setLastName(t.getLastName());
		dto.setEmpPosition(t.getEmpPosition());
		dto.setUserName(t.getUserName());
		dto.setEmail(t.getEmail());
		dto.setPassword(t.getPassword());
		dto.setAddress(t.getAddress());
		dto.setMobileNo(t.getMobileNo());
		dto.setSalary(t.getSalary());
		dto.setJoinDate(t.getJoinDate());
		dto.setStatus(StatusType.Active.toString());
		dto.setCreated(t.getCreated());
		dto.setModified(t.getModified());

		 if (t.getRoles() != null && !t.getRoles().isEmpty()) {
	            String[] rolesArray = t.getRoles().stream()
	                    .map(role -> role.getRole()) // Assuming Role has a method to get the role name
	                    .toArray(String[]::new);
	            dto.setRoles(rolesArray);
	        }
		
		return dto;
	}

}
