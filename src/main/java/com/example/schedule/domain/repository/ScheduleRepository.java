package com.example.schedule.domain.repository;

import com.example.schedule.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 일정(Schedule) 데이터베이스 접근을 위한 Repository
 */

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
