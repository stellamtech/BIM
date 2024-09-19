package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.CategoryDto;
import com.tw.service.CategoryService;



@RestController
@RequestMapping("/category/")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("save")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto dto ){
		
		return categoryService.saveCategory(dto);
	}
	
	@GetMapping("list")
	public ResponseEntity<?> getCategories(){
		
		return categoryService.getCategories();
	}
	
	@GetMapping("byId/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		return categoryService.getById(id);
	}
	
	@GetMapping("deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		return categoryService.deleteById(id);
	}
}
