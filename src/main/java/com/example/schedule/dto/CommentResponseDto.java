package com.example.schedule.dto;

import com.example.schedule.domain.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 응답 데이터를 담는 DTO 클래스
 */

@Getter
public class CommentResponseDto {

    private Long id;
    private String content;
    private String username;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 댓글 엔티티를 DTO로 변환하는 생성자
     *
     * @param comment 댓글 엔티티
     */

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.scheduleId = comment.getSchedule().getId();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}

