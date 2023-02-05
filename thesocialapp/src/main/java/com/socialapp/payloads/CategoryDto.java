package com.socialapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter @Setter
public class CategoryDto {

	
	private Integer categoryId;
	
	@NotBlank @Size(min = 4,message = "Minimum size of title should be 4")
	private String categoryTitle;
	
	@NotBlank @Size(min = 4,message = "Minimum size of description should be 10")
	private String categoryDescription;
	
	
}
