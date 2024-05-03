package com.blogapplication.project.service;

import java.util.List;

import com.blogapplication.project.entity.Category;
import com.blogapplication.project.exception.CategoryNotFoundException;

public interface CategoryService {
   
	Category createCategory(Category category);
	
	Category getCategoryById(int categoryId) throws CategoryNotFoundException;
	
	List<Category> getAllCategory();
	
	Category updateCategory(Category category,int CategoryId) throws CategoryNotFoundException;
	
	void deleteCategory(int categoryId) throws CategoryNotFoundException;
}
