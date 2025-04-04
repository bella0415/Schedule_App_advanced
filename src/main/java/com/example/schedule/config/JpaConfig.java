package com.example.schedule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing을 활성화하는 설정 클래스
 * 생성일, 수정일 자동 기록 기능을 사용할 수 있게 됨
 */

@Configuration
@EnableJpaAuditing
public class JpaConfig {
}