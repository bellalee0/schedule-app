package com.example.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String contents;
    private String creator;
    private Long password;

    public CreateScheduleRequest(String title, String contents, String creator, Long password) {
        this.title = title;
        this.contents = contents;
        this.creator = creator;
        this.password = password;
    }
}