package com.socialapp.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 1, message = "Username must be atleast 1 character.")
	private String name;
	
	@Email(message = "Email should be valid.")
	private String email;
	
	@NotEmpty
	@Size(min = 8,max=16,message = "Password must be min of 8 characters and max of 16 characters.")
	private String password;
	
	@NotEmpty
	private String about;
	
}
