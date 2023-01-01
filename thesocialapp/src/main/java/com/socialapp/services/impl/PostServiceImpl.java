package com.socialapp.services.impl;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) throws IOException {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());
        post.setPostImage(postDto.getPostImage());
        post.setPostSize(postDto.getPostSize());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setUpdatedDate(new Date());
        Post savedPost = this.postRepo.save(post);

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        this.postRepo.delete(post);

    }

    @Override
    public List<PostDto> getAllPost() {

        List<Post> posts = this.postRepo.findAll(Sort.by("updatedDate").descending());

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postid) {
        Post post = this.postRepo.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postid));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
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
