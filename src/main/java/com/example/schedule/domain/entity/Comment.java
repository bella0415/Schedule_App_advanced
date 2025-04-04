package com.example.schedule.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 댓글 정보를 나타내는 엔티티 클래스
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 댓글 내용
     */

    @Column(nullable = false)
    private String content;

    /**
     * 댓글 작성자 (User와 단방향 연관관계)
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 어떤 일정에 대한 댓글인지 (Schedule과 단방향 연관관계)
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * 댓글 생성자
     *
     * @param content 댓글 내용
     * @param user 댓글 작성 유저
     * @param schedule 댓글이 달린 일정
     */

    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

    /**
     * 댓글 수정 메서드
     *
     * @param content 수정할 내용
     */

    public void update(String content) {
        this.content = content;
    }
}