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
 * 일정 정보를 나타내는 엔티티
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * Schedule 생성자
     * @param title 일정 제목
     * @param content 일정 내용
     * @param user 작성한 유저
     */

    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    /**
     * 일정 정보 수정 메서드
     * @param title 변경할 제목
     * @param content 변경할 내용
     */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}