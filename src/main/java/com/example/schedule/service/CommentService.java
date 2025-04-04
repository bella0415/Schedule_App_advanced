package com.example.schedule.service;

import com.example.schedule.domain.entity.Comment;
import com.example.schedule.domain.entity.Schedule;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.CommentRepository;
import com.example.schedule.domain.repository.ScheduleRepository;
import com.example.schedule.domain.repository.UserRepository;
import com.example.schedule.dto.CommentRequestDto;
import com.example.schedule.dto.CommentResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 댓글 관련 비즈니스 로직을 처리하는 서비스 클래스
 */

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 댓글 생성 메서드
     *
     * @param requestDto 댓글 생성 요청 데이터
     */

    @Transactional
    public void createComment(CommentRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        Comment comment = new Comment(requestDto.getContent(), user, schedule);
        commentRepository.save(comment);
    }

    /**
     * 댓글 조회 로직
     */

    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<CommentResponseDto> findByScheduleId(Long scheduleId) {
        return commentRepository.findAllByScheduleId(scheduleId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 댓글 수정 메서드
     *
     * @param id 댓글 ID
     * @param content 수정할 댓글 내용
     */

    @Transactional
    public void updateComment(Long id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        comment.update(content);
    }

    /**
     * 댓글 삭제 메서드
     *
     * @param id 댓글 ID
     */

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}