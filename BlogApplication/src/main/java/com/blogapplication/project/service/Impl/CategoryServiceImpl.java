package com.blogapplication.project.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.project.entity.Category;
import com.blogapplication.project.exception.CategoryNotFoundException;
import com.blogapplication.project.repository.CategoryRepository;
import com.blogapplication.project.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category createCategory(Category category) {
		 category.setCreatedAt(new Date());
		Category save = categoryRepository.save(category);
		return save;
	}

	@Override
	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
	    return category;
	}

	@Override
	public List<Category> getAllCategory() {
		 List<Category> allCategory = categoryRepository.findAll();
		 return allCategory;
	}

	@Override
	public Category updateCategory(Category category, int categoryId) throws CategoryNotFoundException {
		Category updateCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
        
		updateCategory.setCategoryTitle(category.getCategoryTitle());
		updateCategory.setCategoryDesc(category.getCategoryDesc());
		
		Category save = categoryRepository.save(updateCategory);
		return save;
	}

	@Override
	public void deleteCategory(int categoryId) throws CategoryNotFoundException {
		Category Category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
		categoryRepository.delete(Category);
	}

}
