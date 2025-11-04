package com.example.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private String creator;
    private Long password;
}