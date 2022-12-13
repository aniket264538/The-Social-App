package com.socialapp.services;

import java.util.List;

import com.socialapp.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId);
	
	CategoryDto getCategoryById(Integer categoryId);
	
	List<CategoryDto> getallCategory();
	
	void deleteCategory(Integer categoryId);
	
	
	
}
