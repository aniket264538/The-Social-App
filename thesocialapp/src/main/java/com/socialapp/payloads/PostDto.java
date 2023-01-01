package com.socialapp.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private Date updatedDate;

	private byte[] postImage;

	private Long postSize;
	
	private CategoryDto category;
	
	private UserDto user;

	public PostDto(String title, String content)
	{
		this.title =title;
		this.content=content;
	}
	
}
