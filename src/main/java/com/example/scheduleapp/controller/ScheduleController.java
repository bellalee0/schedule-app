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

    /**
     * 일정 생성하기
     *
     * @param request HTTP의 body로 전달된 내용을 request DTO로 받아오기
     * @return Service의 create 실행 후 response DTO에 담아서 반환
     */
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * 일정 조회하기
     *
     * @param creator Request 파라미터로 작성자명 받기(선택)
     * @return Service의 getAllSchedules 실행 후 response DTO에 담아서 반환
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(@RequestParam(name = "creator", required = false) String creator) {
        List<GetScheduleResponse> result = scheduleService.getAllSchedules(creator);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 선택 일정 조회하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @return Service의 getOneSchedule 실행 후 response DTO에 담아서 반환
     */
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        GetOneScheduleResponse result = scheduleService.getOneSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 선택 일정 수정하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @param request HTTP의 body로 전달된 내용을 request DTO로 받아오기
     * @return Service의 updateSchedule 실행 후 response DTO에 담아서 반환
     */
    @PutMapping("schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 선택 일정 삭제하기
     *
     * @param scheduleId API Path로 일정ID 입력받기
     * @param password Request 파라미터로 비밀번호 받기(필수)
     * @return Void 클래스로 빈값 반환
     */
    @DeleteMapping("schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam(name = "password", defaultValue = "0") Long password) {
        scheduleService.deleteSchedule(scheduleId, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
