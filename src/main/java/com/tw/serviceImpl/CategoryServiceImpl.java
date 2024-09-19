package com.tw.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tw.dto.CategoryDto;
import com.tw.entity.Category;
import com.tw.generics.Code;
import com.tw.generics.Response;
import com.tw.repository.CategoryRepository;
import com.tw.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public ResponseEntity<?> saveCategory(CategoryDto dto) {

		Category c = new Category();

		if (dto.getId() != null && dto.getId() > 0) {
			Optional<Category> cat = categoryRepo.findById(dto.getId());
			c = cat.get();
			c.setModified(Calendar.getInstance());
		} else {
			c.setCreated(Calendar.getInstance());
			c.setModified(Calendar.getInstance());
		}
		c.setCategoryCode(dto.getCategoryCode());
		c.setCategoryName(dto.getCategoryName());

		categoryRepo.save(c);

		return Response.build(Code.CREATED, "created successfully!");
	}

	@Override
	public ResponseEntity<?> getCategories() {

		List<Category> li = categoryRepo.findAll();
		return Response.build(Code.OK, li);
	}

	@Override
	public ResponseEntity<?> getById(Long id) {

		Optional<Category> optionalCategory = categoryRepo.findById(id);
		Category category = optionalCategory.get();

		return Response.build(Code.OK, category);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		Category category = optionalCategory.get();
		category.setDeleted(true);
		
		categoryRepo.save(category);
		
		return Response.build(Code.OK, "Deleted success!");
	}

}
