package com.socialapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialapp.entities.Category;
import com.socialapp.exceptions.ResourceNotFoundException;
import com.socialapp.payloads.CategoryDto;
import com.socialapp.repositories.CategoryRepo;
import com.socialapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId) {

		Category category = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));

		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());

		Category updatedCategory = this.categoryRepo.save(category);

		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getallCategory() {

		List<Category> categories = (List<Category>) this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return catDtos;
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.delete(category);
	}

}
