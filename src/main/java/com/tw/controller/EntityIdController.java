package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.service.EntityIdService;

@RestController
@RequestMapping("rest/api/")
public class EntityIdController {

	@Autowired
	private EntityIdService entityIdService;
	
	@GetMapping(value = "entityidrep/{type}")
	public ResponseEntity<?> entityidrep(@PathVariable String type) {
		return entityIdService.entityidrep(type);
	}
	
	@GetMapping(value = "getvocher/{type}")
	public String getvocher(@PathVariable String type) {
		return entityIdService.getvocher(type);
	}
}
