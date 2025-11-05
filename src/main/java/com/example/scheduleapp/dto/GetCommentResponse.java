package com.example.scheduleapp.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private Long commentId;
    private String comment;
    private String commentCreator;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(Long commentId, String comment, String commentCreator, Long commentPassword, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.commentCreator = commentCreator;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
