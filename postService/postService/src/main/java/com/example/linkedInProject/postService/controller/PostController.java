package com.example.linkedInProject.postService.controller;


import com.example.linkedInProject.postService.dto.PostCreateRequestDto;
import com.example.linkedInProject.postService.dto.PostDTO;
import com.example.linkedInProject.postService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest)
    {
        PostDTO postDTO = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long postId)
    {
        PostDTO postDto = postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDTO>> getAllPostsOfUser(@PathVariable Long userId)
    {
        List<PostDTO> posts = postService.getAllPostOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
