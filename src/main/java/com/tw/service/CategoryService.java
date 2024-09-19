package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.CategoryDto;

public interface CategoryService {

	ResponseEntity<?> saveCategory(CategoryDto dto);

	ResponseEntity<?> getCategories();

	ResponseEntity<?> getById(Long id);

	ResponseEntity<?> deleteById(Long id);

}
