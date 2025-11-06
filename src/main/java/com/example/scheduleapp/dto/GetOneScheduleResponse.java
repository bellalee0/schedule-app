package com.example.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  선택(단건) 일정 조회 응답 처리 DTO (일정Id, 일정 제목, 일정 내용, 작성자, 생성일, 수정일, 댓글 리스트)
 */
@Getter
public class GetOneScheduleResponse {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final String creator;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> comments;

    public GetOneScheduleResponse(Long scheduleId, String title, String contents, String creator, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentResponse> comments) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.creator = creator;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
