package com.example.scheduleapp.dto;

import lombok.Getter;

/**
 *  일정 수정 요청 처리 DTO (일정 제목, 작성자)
 */
@Getter
public class UpdateScheduleRequest {
    private String title;
    private String creator;
}
