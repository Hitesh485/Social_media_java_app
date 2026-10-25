package com.example.linkedInProject.postService.service;


import com.example.linkedInProject.postService.entity.Post;
import com.example.linkedInProject.postService.entity.PostLike;
import com.example.linkedInProject.postService.exception.ResourceNotFoundException;
import com.example.linkedInProject.postService.repository.PostLikeRepository;
import com.example.linkedInProject.postService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.Exception;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService extends Exception {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId) throws Exception {
        Long userId = 1L;        // temp ->
        log.info("User with id: {} liking the post with id: {}", userId, postId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + postId)
        );

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        // Post should dislike if user like the post again
        try {
            if (hasAlreadyLiked) {
                throw new Exception("You cannot like post again");
            } else {
                PostLike postLike = new PostLike();
                postLike.setPostId(postId);
                postLike.setUserId(userId);
                postLikeRepository.save(postLike);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        if (hasAlreadyLiked)
//        {
//            unlikePost(postId);
//        }


        // TODO: Send notifications to user & maintain count of like on post.

    }

    @Transactional
    public void unlikePost(Long postId) throws Exception {
        Long userId = 1L;
        log.info("User with id: {} unliking the post with id: {}", userId, postId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + postId)
        );

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        // Post should dislike if user like the post again
        try {
            if (!hasAlreadyLiked) {
                throw new Exception("You cannot unlike post again");
            } else {
                postLikeRepository.deleteByUserIdAndPostId(userId, postId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
