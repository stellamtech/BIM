package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.UserDto;
import com.tw.dto.UserSpecDto;
import com.tw.exception.UserAlreadyExistsException;
import com.tw.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.websocket.server.PathParam;
@ApiResponse
@RestController
@RequestMapping("/user/")
@SecurityRequirements(value = { @SecurityRequirement(name = "bearerAuth") })
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping(value = "save")
	public ResponseEntity<?>  saveUser(@RequestBody UserDto dto)  throws UserAlreadyExistsException{
		return userservice.saveUser(dto);
	}
	
	@PostMapping(value = "edit")
	public ResponseEntity<?> editUser(@RequestBody UserDto dto) throws UserAlreadyExistsException {
		return userservice.editUser(dto);
	}
	
	@PostMapping(value = "resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody UserDto dto) {
		return userservice.resetPassword(dto);
	}
	@PostMapping(value = "listUser")
	public ResponseEntity<?>  listUser (@RequestBody UserSpecDto dto) {
		return userservice.listUser(dto);
	}

	@GetMapping(value = "delete")
	public ResponseEntity<?> delete(@PathParam("id") Long id) {
		return userservice.delete(id);

	}

	@GetMapping(value = "deactive/{id}")
	public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
		return userservice.deactivateUser(id);
	}
	
	@GetMapping(value = "getbyid")
	public ResponseEntity<?> getUserById(@PathParam("id") Long id) {
		return userservice.findById(id);

	}
	
	@GetMapping(value = "listRole")
	public ResponseEntity<?>  listRole () {
		return userservice.listRole();
	}
	
	@GetMapping(value = "listUserSimple")
	public ResponseEntity<?> listUserSimple (){
		return userservice.listUserSimple();
	}
}
