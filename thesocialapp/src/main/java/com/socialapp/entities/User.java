package com.socialapp.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "first_name", nullable = false, length = 100)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 100)
	private String lastName;

	@Column(name = "phone_number", nullable = false, length = 10)
	private Long phoneNumber;

	@Column(length = 100)
	private String occupation;

	@Email
	@NotEmpty(message = "This email is already being used.")
	@Column(unique = true)
	private String email;

	@Lob
	@Column(name="image", nullable = false, columnDefinition = "MEDIUMBLOB")
	private byte[] profileImage;

	@NotEmpty
//	@Size(min = 8,max=300,message = "Password must be min of 8 characters and max of 16 characters.")
	private String password;

	@NotEmpty
	private String about;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> posts = new ArrayList<>();


	private List<Post> savedPosts = new ArrayList<>();
	@OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comment> comment;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user", referencedColumnName = "userid"),
			inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id")
	)
	private  Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> simpleGrantedAuthorities = this.roles.stream().map(
				(role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return simpleGrantedAuthorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
