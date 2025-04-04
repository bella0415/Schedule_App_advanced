package com.example.schedule.controller;

import com.example.schedule.dto.CommentRequestDto;
import com.example.schedule.dto.CommentResponseDto;
import com.example.schedule.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 관련 HTTP 요청을 처리하는 controller 클래스
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성 API
     *
     * @param requestDto 댓글 요청 데이터
     * @return 생성 결과 메시지
     */

    @PostMapping
    public String createComment(@RequestBody @Valid CommentRequestDto requestDto) {
        commentService.createComment(requestDto);
        return "댓글 등록 성공";
    }

    @GetMapping
    public List<CommentResponseDto> getAllComments() {
        return commentService.findAll();
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<CommentResponseDto> getCommentsBySchedule(@PathVariable Long scheduleId) {
        return commentService.findByScheduleId(scheduleId);
    }

    /**
     * 댓글 수정 API
     *
     * @param id 댓글 ID
     * @param content 수정할 내용 (단순 문자열)
     * @return 수정 완료 메시지
     */

    @PutMapping("/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody String content) {
        commentService.updateComment(id, content);
        return "댓글 수정 성공";
    }

    /**
     * 댓글 삭제 API
     *
     * @param id 댓글 ID
     * @return 삭제 완료 메시지
     */

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "댓글 삭제 성공";
    }


}

