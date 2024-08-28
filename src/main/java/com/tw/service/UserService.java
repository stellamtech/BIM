package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.UserDto;
import com.tw.dto.UserSpecDto;
import com.tw.exception.UserAlreadyExistsException;

public interface UserService {

	public  ResponseEntity<?> saveUser(UserDto dto) throws UserAlreadyExistsException;

	public ResponseEntity<?> editUser(UserDto dto) throws UserAlreadyExistsException;
	
	public ResponseEntity<?> resetPassword(UserDto dto);
	
	public ResponseEntity<?> listUser(UserSpecDto dto);
	
	public ResponseEntity<?> listUserSimple();
		
	public ResponseEntity<?> findById(Long id);

	public ResponseEntity<?> delete(Long id);

	public ResponseEntity<?> deactivateUser(Long id);

	public ResponseEntity<?> listRole();
	
}
