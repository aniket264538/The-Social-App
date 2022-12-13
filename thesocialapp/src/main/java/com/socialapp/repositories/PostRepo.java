package com.socialapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialapp.entities.Category;
import com.socialapp.entities.Post;
import com.socialapp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Category> findByCategory(Category category);

}
