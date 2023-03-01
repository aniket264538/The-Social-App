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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts/")
@CrossOrigin("http://localhost:3000")
public class BookmarkController {

    @Autowired
    private BookmarkRepo bookmarkRepository;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("bookmark/{userId}/post/{postId}")
    public ResponseEntity<Bookmark> bookmarkPost(@RequestParam Long postId, @RequestParam Long userId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setPostId(postId);
        bookmark.setUserId(userId);
        return new ResponseEntity<>(bookmarkRepository.save(bookmark), HttpStatus.CREATED);
    }

    @GetMapping("bookmarks/{userId}")
    public ResponseEntity<List<PostDto>> getAllBookmarkedPosts(@RequestParam Long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
        List<Long> postIds = bookmarks.stream().map(Bookmark::getPostId).collect(Collectors.toList());
        List<Post> savedPost = postRepo.findAllById(postIds);
        List<PostDto> postDtos = savedPost.stream().map(post ->
                this.modelMapper.map(savedPost, PostDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
