package com.example.linkedInProject.postService.service;

import com.example.linkedInProject.postService.dto.PostCreateRequestDto;
import com.example.linkedInProject.postService.dto.PostDTO;
import com.example.linkedInProject.postService.entity.Post;
import com.example.linkedInProject.postService.exception.ResourceNotFoundException;
import com.example.linkedInProject.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    // userId will come in header
    public PostDTO createPost(PostCreateRequestDto postCreateRequestDto, Long userId)
    {
        log.info("Creating post for user with id : {}", userId);
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDTO.class);
    }

    public PostDTO getPostById(Long postId)
    {
        log.info("Getting the post with id: {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: " + postId));
        return modelMapper.map(post, PostDTO.class);
    }


    public List<PostDTO> getAllPostOfUser(Long userId)
    {
        log.info("Getting all the posts of a user with id: {}", userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList
                .stream()
                .map((element)-> modelMapper.map(element, PostDTO.class))
                .collect(Collectors.toList());
    }
}
