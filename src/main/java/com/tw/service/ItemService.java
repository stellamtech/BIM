package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.ItemDto;

public interface ItemService {

	ResponseEntity<?> saveItem(ItemDto dto);

	ResponseEntity<?> getItems();

	ResponseEntity<?> getItemById(Long id);

	ResponseEntity<?> deleteItem(Long id);

}
