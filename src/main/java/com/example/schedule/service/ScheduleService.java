package com.example.schedule.service;

import com.example.schedule.domain.entity.Schedule;
import com.example.schedule.domain.entity.User;
import com.example.schedule.domain.repository.ScheduleRepository;
import com.example.schedule.domain.repository.UserRepository;
import com.example.schedule.dto.SchedulePageResponseDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    /**
     * 일정 생성 메서드
     *
     * @param requestDto 일정 생성 요청 정보
     */
    @Transactional
    public void createSchedule(ScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                user
        );

        scheduleRepository.save(schedule);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }


}
