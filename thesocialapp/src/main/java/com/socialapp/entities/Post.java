package com.socialapp.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
	@Column(length = 100,nullable = false)
	private String title;
	
	@Column(length = 10000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@Column(nullable = true)
	private Date updatedDate;

	@Lob
	@Column(name="image", nullable = false)
	private byte[] postImage;

	private Long postSize;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "category_id" , nullable = false)
	private Category category;
	
	@ManyToOne
	private User user;
	
}
