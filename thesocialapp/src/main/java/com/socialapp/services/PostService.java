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
	List<Post> getAllPost();

	// Get Single Post
	Post getPostById(Integer postid);
	
	// Get all Posts by Category
	List<Post> getAllPostByCategory(Integer categoryId);
	
	//Get all Posts by User
	List<Post> getAllPostByUser(Integer userId);
	
	//Searching sort using Keyword
	List<Post> searchPosts(String keyword);

}
