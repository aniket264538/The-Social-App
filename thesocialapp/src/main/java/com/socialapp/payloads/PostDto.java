package com.socialapp.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.socialapp.entities.Comment;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Long postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private Date updatedDate;

	private byte[] postImage;

	private Long postSize;

//	private Set<Comment> comments = new HashSet<>();
	
	private CategoryDto category;
	
	private UserDto user;

	public PostDto(String title, String content)
	{
		this.title =title;
		this.content=content;
	}
	
}
