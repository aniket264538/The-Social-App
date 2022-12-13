package com.socialapp.services;

import com.socialapp.payloads.UserDto;

import java.util.List;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
}
