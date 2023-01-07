package com.socialapp.controllers;

import com.socialapp.payloads.ApiResponse;
import com.socialapp.payloads.CommentDto;
import com.socialapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("posts/{postId}/user/{userId}/comment")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long postId,
            @PathVariable("userId") long commenterId)
    {

        CommentDto comment = this.commentService.createComment(commentDto, postId, commenterId);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }


    @DeleteMapping("delete/comment/{commentId}")
    public ApiResponse deleteComment(
            @PathVariable Long commmentId)
    {

        this.commentService.deleteComment(commmentId);

        return new ApiResponse("Comment is succesfully deleted!!", true);

    }

    @GetMapping("posts/{postId}/comments")
    public ResponseEntity<Set<CommentDto>> getCommentsOnPost (@PathVariable long postId)
    {
        Set<CommentDto> commentsOnPost = this.commentService.getCommentsOnPost(postId);

        return new ResponseEntity<>(commentsOnPost, HttpStatus.OK);
    }
}
