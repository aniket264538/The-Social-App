package com.socialapp.payloads;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class   PostDto {

	private Long postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private Date updatedDate;

//	private String postImage;
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

//	public byte[] getPostImage(){
//		return postImage.getBytes();
//	}
	
}
