package com.example.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 *  댓글 생성 응답 처리 DTO (댓글Id, 일정Id, 댓글, 작성자, 생성일, 수정일)
 */
@Getter
public class CreateCommentResponse {
    private final Long commentId;
    private final Long scheduleId;
    private final String comment;
    private final String commentCreator;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Long commentId, Long scheduleId, String comment, String commentCreator, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.scheduleId = scheduleId;
        this.comment = comment;
        this.commentCreator = commentCreator;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
