package com.socialapp.services;

import com.socialapp.payloads.PostDto;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PostService {

	// Create Post
	PostDto createPost(PostDto postDto, Long userId, Long categoryId) throws IOException;



    // Update Post
	PostDto updatePost(PostDto postDto, Long postId);

	// Delete post
	void deletePost(Long postId);

	// Get all Posts
	List<PostDto> getAllPost();

	// Get Single Post
	PostDto getPostById(Long postid);
	
	// Get all Posts by Category
	List<PostDto> getAllPostByCategory(Long categoryId);

	Set<PostDto> getPostsofMultipleCategory(Long[] categoryId);
	
	//Get all Posts by User
	List<PostDto> getAllPostByUser(Long userId);
	
	//Searching sort using Keyword
	List<PostDto> searchPosts(String keyword);

}
