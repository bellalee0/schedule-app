package com.example.scheduleapp.dto;

import lombok.Getter;

/**
 *  일정 생성 요청 처리 DTO (일정 제목, 일정 내용, 작성자, 비밀번호)
 */
@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private String creator;
    private Long password;
}