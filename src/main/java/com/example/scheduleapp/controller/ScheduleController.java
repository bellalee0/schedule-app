package com.example.scheduleapp.controller;

import com.example.scheduleapp.dto.*;
import com.example.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 일정 조회 (전체 일정 조회 or creator 입력 시 해당 creator의 일정 조회)
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(@RequestParam(name = "creator", required = false) String creator) {
        List<GetScheduleResponse> result = scheduleService.getAllSchedules(creator);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 입력받은 id의 일정 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        GetOneScheduleResponse result = scheduleService.getOneSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 압력받은 id의 일정 수정
    @PutMapping("schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestParam(name = "password") String password,
            @RequestBody UpdateScheduleRequest request) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, password, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 입력받은 id의 일정 삭제
    @DeleteMapping("schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam(name = "password") String password) {
        scheduleService.deleteSchedule(scheduleId, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
