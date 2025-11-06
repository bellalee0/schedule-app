package com.example.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 *  일정 수정 응답 처리 DTO (일정Id, 일정 제목, 일정 내용, 작성자, 생성일, 수정일)
 */
@Getter
public class UpdateScheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final String creator;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Long scheduleId, String title, String contents, String creator, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.creator = creator;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
