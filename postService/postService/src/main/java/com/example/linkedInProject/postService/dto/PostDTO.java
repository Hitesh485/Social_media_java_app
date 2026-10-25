package com.example.linkedInProject.postService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;

}
