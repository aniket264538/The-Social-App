package com.socialapp.services;

import java.util.List;

import com.socialapp.entities.Post;
import com.socialapp.payloads.PostDto;

public interface PostService {

	// Create Post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update Post
	PostDto updatePost(PostDto postDto, Integer postId);

	// Delete post
	void deletePost(Integer postId);

	// Get all Posts
	List<PostDto> getAllPost();

	// Get Single Post
	PostDto getPostById(Integer postid);
	
	// Get all Posts by Category
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	//Get all Posts by User
	List<PostDto> getAllPostByUser(Integer userId);
	
	//Searching sort using Keyword
	List<PostDto> searchPosts(String keyword);

}
