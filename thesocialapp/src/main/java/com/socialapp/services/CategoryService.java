package com.socialapp.services;

import java.util.List;

import com.socialapp.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Long CategoryId);
	
	CategoryDto getCategoryById(Long categoryId);
	
	List<CategoryDto> getallCategory();
	
	void deleteCategory(Long categoryId);
	
	
	
}
