package com.socialapp.services;

import com.socialapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Long CategoryId);
	
	CategoryDto getCategoryById(Long categoryId);
	
	List<CategoryDto> getallCategory();
	
	void deleteCategory(Long categoryId);
	
	
	
}
