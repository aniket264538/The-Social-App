package com.socialapp.controllers;

import com.socialapp.payloads.ApiResponse;
import com.socialapp.payloads.CategoryDto;
import com.socialapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> categoryById = this.categoryService.getallCategory();
		return ResponseEntity.ok(categoryById);

	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Long categoryId) {

		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	//Delete Mapping for Category Id
	  @DeleteMapping("/{categoryId}") public ResponseEntity<ApiResponse>
	  deleteCategory(@PathVariable Long categoryId) {
		  
	  this.categoryService.deleteCategory(categoryId); 
	  return new ResponseEntity<ApiResponse>(new ApiResponse("Deletion Successfull.",true),HttpStatus.OK);
	  
	  }
	 

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId)
	{
		CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);
		return ResponseEntity.ok(categoryById);
		
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
