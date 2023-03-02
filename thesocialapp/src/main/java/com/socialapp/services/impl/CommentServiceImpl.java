package com.socialapp.services.impl;

import com.socialapp.entities.Comment;
import com.socialapp.entities.Post;
import com.socialapp.entities.User;
import com.socialapp.exceptions.ResourceNotFoundException;
import com.socialapp.payloads.CommentDto;
import com.socialapp.payloads.PostDto;
import com.socialapp.payloads.UserDto;
import com.socialapp.repositories.CommentRepo;
import com.socialapp.repositories.PostRepo;
import com.socialapp.repositories.UserRepo;
import com.socialapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId, Long commentorId) {

        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));


        User commenter = this.userRepo.findById(commentorId).
                orElseThrow(()->new ResourceNotFoundException("User", "User Id", commentorId));

        UserDto map = this.modelMapper.map(commenter, UserDto.class);
        System.out.println(map);

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setCommenter(commenter);
        comment.setDate(new Date());

        Comment createdComment = this.commentRepo.save(comment);

        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public Set<CommentDto> getCommentsOnPost(Long postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
//        System.out.println(post);
//        System.out.println("Found post");
        PostDto map1 = this.modelMapper.map(post, PostDto.class);
        System.out.println(map1);

        Set<Comment> comments = this.commentRepo.findByPost(post);
//        System.out.println("Comments Found");
//        System.out.println(comments);

        Set<CommentDto> commentDtoSet = comments.stream().map(comment ->
                this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toSet());

        System.out.println(commentDtoSet);

        return commentDtoSet;
    }

    @Override
    public void deleteComment(Long commentId) {

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

        this.commentRepo.delete(comment);

    }
}
