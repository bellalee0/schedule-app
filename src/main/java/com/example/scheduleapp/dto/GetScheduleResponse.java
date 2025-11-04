package com.example.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final String creator;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetScheduleResponse(Long scheduleId, String title, String contents, String creator, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.creator = creator;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
