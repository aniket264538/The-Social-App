package com.socialapp.controllers;

import com.socialapp.payloads.ApiResponse;
import com.socialapp.payloads.PostDto;
import com.socialapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:3000")
public class PostController {

    @Autowired
    PostService postService;
    private List<PostDto> searchPosts;

    // Create Post
    @PostMapping("user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
			/*@RequestBody PostDto postDto,*/
            @RequestParam String title,
            @RequestParam String caption,
            @RequestParam String content,
			@RequestParam MultipartFile image,
            @PathVariable Long userId,
            @PathVariable Long categoryId
            ) throws IOException {
        System.out.println("Reached inside controller");
        PostDto postDto = new PostDto(title, content, caption);

        /*   postDto.setPostImage(Base64.getEncoder().
          encodeToString(postDto.getPostImage().getBytes()));*/      /*Method - 1*/
//        postDto.setPostSize(image.getSize());
//        postDto.setImageName(StringUtils.cleanPath(image.getOriginalFilename()));

//        postDto.setPostImage(compressBytes((image.getBytes())));  method - 2

        postDto.setPostImage(image.getBytes());
        postDto.setPostSize(image.getSize());
        postDto.setImageName(image.getOriginalFilename());

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    // Getting all post by a User
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
            @PathVariable Long userId) {

        List<PostDto> posts = this.postService.getAllPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Getting all post of a Category
/*    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(
            @PathVariable Long categoryId) {

        List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }*/

    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<Set<PostDto>> getPostsOfMultipleCategory(
            @PathVariable Long[] categoryId) {

        Set<PostDto> posts = this.postService.getPostsofMultipleCategory(categoryId);

        return new ResponseEntity<Set<PostDto>>(posts, HttpStatus.OK);
    }

    // Getting all the posts
    @GetMapping("posts")
    public ResponseEntity<List<PostDto>> getAllPost() {

        List<PostDto> postDtos = this.postService.getAllPost();

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    // Getting post by postID
    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {

        PostDto post = this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(post, HttpStatus.OK);

    }

    // Delete post using Id
    @DeleteMapping("posts/{postId}")
    public ApiResponse deletePost(@PathVariable Long postId) {
        this.postService.deletePost(postId);

        return new ApiResponse("Post is successfully deleted !!", true);
    }

    // Updating Post using PostId
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long postId) {
        PostDto updatePost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    // Searching Post using keywords
    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword) {

        List<PostDto> postDtos = this.postService.searchPosts(keyword);

        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
