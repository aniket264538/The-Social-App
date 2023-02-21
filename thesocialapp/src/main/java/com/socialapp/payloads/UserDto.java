package com.socialapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	
	private long id;
	
	@NotEmpty
	@Size(min = 1, message = "Username must be atleast 1 character.")
	private String firstName;

	@NotEmpty
	@Size(min = 1, message = "Username must be atleast 1 character.")
	private String lastName;
	
	@Email(message = "Email should be valid.")
	private String email;

	@Column(name = "phone_number", nullable = false, length = 10)
	private Long phoneNumber;

	@Column(length = 100)
	private String occupation;
	
	@NotEmpty
	@Size(min = 8,max=16,message = "Password must be min of 8 characters and max of 16 characters.")
	private String password;
	
	@NotEmpty
	private String about;

	/*@JsonIgnore
	public String getPassword(){
		return this.password;
	}*/

}
