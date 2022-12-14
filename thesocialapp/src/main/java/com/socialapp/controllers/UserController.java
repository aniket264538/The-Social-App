package com.socialapp.controllers;

import com.socialapp.payloads.ApiResponse;
import com.socialapp.payloads.UserDto;
import com.socialapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	// POST - create user
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userService.createUser(userDto);
		return  new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//GET - get user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUser()
	{
		List<UserDto> getUserDtos = this.userService.getAllUser();
		return new ResponseEntity<>(getUserDtos,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	//PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId)
	{
		
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	//Delete - Delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
	}
}
