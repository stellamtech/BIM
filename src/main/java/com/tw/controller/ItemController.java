package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.ItemDto;
import com.tw.dto.ItemSpecDto;
import com.tw.exception.ItemAlreadyExistsException;
import com.tw.exception.ItemCodeAlreadyExistsException;
import com.tw.service.ItemService;

@RestController
@RequestMapping("/item")
//@CrossOrigin
public class ItemController {

	@Autowired
	private ItemService itemservice;

	@PostMapping("/save")
	public ResponseEntity<?> saveItem(@RequestBody ItemDto dto) throws ItemAlreadyExistsException, ItemCodeAlreadyExistsException {
		return itemservice.saveItem(dto);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getItems(ItemSpecDto spectDto) {
		return itemservice.getItems(spectDto);
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<?> getItemById(@PathVariable Long id) {
		return itemservice.getItemById(id);
	}
	
	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return itemservice.deleteItem(id);

	}
}
