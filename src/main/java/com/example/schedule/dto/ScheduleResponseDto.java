package com.example.schedule.dto;

import com.example.schedule.domain.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 응답 데이터를 담는 DTO 클래스
 */

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username; // 작성자
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Schedule → DTO로 변환하는 생성자
     *
     * @param schedule 일정 엔티티
     */

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.username = schedule.getUser().getUsername();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
