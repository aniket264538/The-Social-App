package com.socialapp.controllers;

import com.socialapp.entities.Bookmark;
import com.socialapp.entities.Post;
import com.socialapp.repositories.BookmarkRepo;
import com.socialapp.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/posts/")
public class BookmarkController {

    @Autowired
    private BookmarkRepo bookmarkRepository;

    @Autowired
    private PostRepo postRepo;

    @PostMapping("bookmarks/{userId}")
    public Bookmark bookmarkPost(@RequestBody Long postId, @RequestParam Long userId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setPostId(postId);
        bookmark.setUserId(userId);
        return bookmarkRepository.save(bookmark);
    }

    @GetMapping("bookmarks/{userId}")
    public List<Post> getAllBookmarkedPosts(@RequestParam Long userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
        List<Long> postIds = bookmarks.stream().map(Bookmark::getPostId).collect(Collectors.toList());
        return postRepo.findAllById(postIds);
    }
}
