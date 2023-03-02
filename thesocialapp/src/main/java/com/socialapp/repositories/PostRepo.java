package com.socialapp.repositories;

import com.socialapp.entities.Category;
import com.socialapp.entities.Post;
import com.socialapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);

	Post findById(long postId);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);

	List<Post> findByTitleContainingOrderByAddedDateDesc(String title);

//	List<Post> findByTitleContainingOrderByAddedDateDesc(String title);


	List<Post> findByTitleContainingOrderByUpdatedDateAsc(String title);

}
