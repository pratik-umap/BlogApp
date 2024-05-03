package com.blogapplication.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.project.entity.Category;
import com.blogapplication.project.exception.CategoryNotFoundException;
import com.blogapplication.project.payload.ApiResponse;
import com.blogapplication.project.payload.CategoryDto;
import com.blogapplication.project.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
//@PreAuthorize("hasRole('Admin')")
public class CategoryController {
   
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping()
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody Category category) {
		try {
			Category createCategory = categoryService.createCategory(category);
			CategoryDto categoryMap = modelMapper.map(createCategory, CategoryDto.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(categoryMap);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		try {
			List<Category> allCategory = categoryService.getAllCategory();
			List<CategoryDto> allCategoryMap = allCategory.stream().map((category)-> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(allCategoryMap);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int id) throws CategoryNotFoundException{
	
			Category category = categoryService.getCategoryById(id);
			CategoryDto categoryMap = modelMapper.map(category, CategoryDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(categoryMap);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable int id,@RequestBody Category category) throws CategoryNotFoundException{

			Category updateCategory = categoryService.updateCategory(category, id);
			CategoryDto categoryMap = modelMapper.map(updateCategory, CategoryDto.class);
			return ResponseEntity.status(HttpStatus.OK).body(categoryMap);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id) throws CategoryNotFoundException{
	
			categoryService.deleteCategory(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category deleted successfully", "true"));
		
	}
	
}
