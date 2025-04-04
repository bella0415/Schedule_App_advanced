package com.example.schedule.dto;

import com.example.schedule.domain.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 유저 응답 데이터를 담는 DTO
 */

@Getter
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}