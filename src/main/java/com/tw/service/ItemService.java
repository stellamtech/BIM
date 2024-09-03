package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.ItemDto;
import com.tw.dto.ItemSpecDto;
import com.tw.exception.ItemAlreadyExistsException;
import com.tw.exception.ItemCodeAlreadyExistsException;

public interface ItemService {

	ResponseEntity<?> saveItem(ItemDto dto) throws ItemAlreadyExistsException, ItemCodeAlreadyExistsException;

	ResponseEntity<?> getItems(ItemSpecDto spectDto);

	ResponseEntity<?> getItemById(Long id);

	ResponseEntity<?> deleteItem(Long id);

}
