package com.example.schedule.domain.repository;

import com.example.schedule.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 댓글 데이터베이스 접근을 위한 Repository
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);

    int countByScheduleId(Long scheduleId);
}