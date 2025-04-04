package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 댓글 생성 및 수정 요청 데이터를 담는 DTO 클래스
 */

@Getter
public class CommentRequestDto {

    /**
     * 댓글 내용 (공백 불가)
     */

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    /**
     * 댓글 작성 유저 ID (필수)
     */

    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long userId;

    /**
     * 댓글이 달릴 일정 ID (필수)
     */

    @NotNull(message = "일정 ID는 필수입니다.")
    private Long scheduleId;
}
