package com.socialapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialapp.entities.User;
import com.socialapp.exceptions.ResourceNotFoundException;
import com.socialapp.payloads.UserDto;
import com.socialapp.repositories.UserRepo;
import com.socialapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user. setOccupation(userDto.getOccupation());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Long userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).
				collect(Collectors.toList());
		
		return userDtos;
		
	}

	@Override
	public void deleteUser(Long userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		this.userRepo.delete(user);

	}
	
	
	
	public User dtoToUser(UserDto userDto) 
	{
		
		User user = this.modelMapper.map(userDto,User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		/*
		 * User user = new User(); 
		 * user.setId(userDto.getId());
		 * user.setName(userDto.getName()); 
		 * user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword()); 
		 * user.setEmail(userDto.getEmail());
		 */
		return user;
	}
	
	public UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		/*
		 * UserDto userDto = new UserDto();
		 * userDto.setId(user.getId()); 
		 * userDto.setName(user.getName());
		 * userDto.setAbout(user.getAbout()); 
		 * userDto.setPassword(user.getPassword());
		 * userDto.setEmail(user.getEmail());
		 */
		return userDto;
		
	}

	
}	
