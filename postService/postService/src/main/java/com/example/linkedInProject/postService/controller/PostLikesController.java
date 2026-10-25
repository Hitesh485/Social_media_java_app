package com.example.linkedInProject.postService.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.linkedInProject.postService.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/likes")
@RequiredArgsConstructor
@RestController
public class PostLikesController extends  Exception{

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId)
    {
        try{
            postLikeService.likePost(postId);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId)
    {
        try{
            postLikeService.unlikePost(postId);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
