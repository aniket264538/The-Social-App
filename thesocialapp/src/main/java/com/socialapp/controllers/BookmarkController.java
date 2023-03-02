package com.socialapp.controllers;

import com.socialapp.entities.Bookmark;
import com.socialapp.entities.Post;
import com.socialapp.payloads.PostDto;
import com.socialapp.repositories.BookmarkRepo;
import com.socialapp.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookmarks")
@CrossOrigin("http://localhost:3000")
public class BookmarkController {

    @Autowired
    private BookmarkRepo bookmarkRepository;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<Bookmark> bookmarkPost(@PathVariable Long postId, @PathVariable Long userId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setPostId(postId);
        bookmark.setUserId(userId);
        return new ResponseEntity<>(bookmarkRepository.save(bookmark), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllBookmarkedPosts(@PathVariable Long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
        /*List<Long> postIds = bookmarks.stream().map(Bookmark::getPostId).collect(Collectors.toList());*/
        List<Long> postIds = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            Long postId = bookmark.getPostId();
            postIds.add(postId);
        }
        List<Post> savedPost = postRepo.findAllById(postIds);
        List<PostDto> postDtos = savedPost.stream().map(post ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
