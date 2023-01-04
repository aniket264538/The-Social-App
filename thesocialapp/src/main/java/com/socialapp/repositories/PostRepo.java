package com.socialapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialapp.entities.Category;
import com.socialapp.entities.Post;
import com.socialapp.entities.User;

public interface PostRepo extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);

	Post findById(long postId);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);

	List<Post> findByTitleContainingOrderByUpdatedDateDesc(String title);

	List<Post> findByTitleContainingOrderByUpdatedDateAsc(String title);

}
