package com.socialapp.services;

import com.socialapp.entities.Comment;
import com.socialapp.entities.Post;
import com.socialapp.payloads.CommentDto;
import com.socialapp.payloads.PostDto;

import java.util.Set;

public interface CommentService {

    Set<CommentDto> getCommentsOnPost(Long postId);

    CommentDto createComment(CommentDto commentDto, Long postId, Long commentorId);

    void deleteComment(Long commentId);

}
