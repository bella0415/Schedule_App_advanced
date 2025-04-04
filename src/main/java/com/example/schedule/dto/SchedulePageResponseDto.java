// 미완성 !!

package com.example.schedule.dto;

import com.example.schedule.domain.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 페이징 일정 응답 DTO
 */

@Getter
public class SchedulePageResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int commentCount;

    public SchedulePageResponseDto(Schedule schedule, int commentCount) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.username = schedule.getUser().getUsername();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.commentCount = commentCount;
    }
}