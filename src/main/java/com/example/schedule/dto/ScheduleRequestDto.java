package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 일정 생성 및 수정 요청 데이터를 담는 DTO 클래스
 *
 * 유효성 검사를 위해 제목은 최대 10자 / 내용은 빈 값일 수 없음
 *
 */

@Getter
public class ScheduleRequestDto {

    /**
     * 일정 제목 (최대 10자, 필수)
     */

    @NotBlank(message = "할일 제목은 필수입니다.")
    @Size(max = 10, message = "할일 제목은 10자 이내여야 합니다.")
    private String title;

    /**
     * 일정 내용 (필수)
     */

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long userId;
}
