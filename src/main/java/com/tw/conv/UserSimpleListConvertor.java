package com.tw.conv;

import java.util.function.Function;

import com.tw.dto.UserListDto;
import com.tw.entity.User;

public class UserSimpleListConvertor  implements Function<User, UserListDto>{

	@Override
	public UserListDto apply(User t) {
		UserListDto dto = new UserListDto();
		
		dto.setId(t.getId());
		dto.setUserName(t.getUserName());
		dto.setMobileNo(t.getMobileNo());
		dto.setEmail(t.getEmail());
		
		return dto;
	}

}
