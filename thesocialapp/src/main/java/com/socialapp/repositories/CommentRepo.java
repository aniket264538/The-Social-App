package com.socialapp.repositories;

import com.socialapp.entities.Comment;
import com.socialapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    Set<Comment> findByPost(Post post);

    Comment findById(long id);
}
