package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관련 HTTP 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 생성 API
     *
     * @param requestDto 일정 요청 데이터
     * @return 생성 결과 메시지
     */

    @PostMapping
    public String createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto) {
        scheduleService.createSchedule(requestDto);
        return "일정 등록 성공";
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.findAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

}
