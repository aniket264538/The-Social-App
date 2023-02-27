package com.socialapp.services.impl;

import com.socialapp.controllers.PostController;
import com.socialapp.entities.Category;
import com.socialapp.entities.Post;
import com.socialapp.entities.User;
import com.socialapp.exceptions.ResourceNotFoundException;
import com.socialapp.payloads.PostDto;
import com.socialapp.repositories.CategoryRepo;
import com.socialapp.repositories.PostRepo;
import com.socialapp.repositories.UserRepo;
import com.socialapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Long userId, Long categoryId) throws IOException {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
//        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());
        post.setUpdatedDate(post.getAddedDate());
//        post.setPostImage(postDto.getPostImage());
//        post.setPostSize(postDto.getPostSize());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setUpdatedDate(new Date());
        Post savedPost = this.postRepo.save(post);

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        this.postRepo.delete(post);

    }

    @Override
    public List<PostDto> getAllPost() {

        List<Post> posts = this.postRepo.findAll(Sort.by("addedDate").descending());

        List<PostDto> postDtos = posts.stream()
                .map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        postDto.setPostImage(PostController.decompressBytes(post.getPostImage()));

        return postDto;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public Set<PostDto> getPostsofMultipleCategory(Long[] categoryId) {
        List<Post> multipleCategoryPostList = new ArrayList<>();
        for (Long category : categoryId) {
            Category categoryById = this.categoryRepo.findById(category)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", category));

            multipleCategoryPostList.addAll(this.postRepo.findByCategory(categoryById));
        }

        List<PostDto> multipleCategoryPostDtoList = multipleCategoryPostList.stream()
                .map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        Collections.sort(multipleCategoryPostDtoList, new sortItems().reversed());

        Set<PostDto> multipleCategoryPostDtoSet = multipleCategoryPostDtoList.stream().collect(Collectors.toSet());


        return multipleCategoryPostDtoSet;
    }

    @Override
    public List<PostDto> getAllPostByUser(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContainingOrderByUpdatedDateDesc(keyword);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

}

class sortItems implements Comparator<PostDto> {

    // Method of this class
    // @Override
    public int compare(PostDto a, PostDto b)
    {

        // Returning the value after comparing the objects
        // this will sort the data in Ascending order
        return a.getAddedDate().compareTo(b.getAddedDate());
    }
}
