package com.socialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialapp.payloads.ApiResponse;
import com.socialapp.payloads.PostDto;
import com.socialapp.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	PostService postService;
	
	
//	Create Post	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
//	Getting all post by a User	
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(
			@PathVariable Integer userId){
		
		List<PostDto> posts= this.postService.getAllPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
//	Getting all post of a Category	
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(
			@PathVariable Integer categoryId){
		
		List<PostDto> posts= this.postService.getAllPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
// 	Getting all the posts
	@GetMapping("posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		
		List<PostDto> postDtos = this.postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
// 	Getting post by postID
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto post = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
		
	}
	
//	Delete post using Id
	
	@DeleteMapping("posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		
		return new ApiResponse("Post is successfully deleted !!", true);
	}

	
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
}
