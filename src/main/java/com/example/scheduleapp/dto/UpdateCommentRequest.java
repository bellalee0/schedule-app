package com.example.scheduleapp.dto;

import lombok.Getter;

/**
 *  댓글 수정 요청 처리 DTO (댓글, 작성자)
 */
@Getter
public class UpdateCommentRequest {
    private String comment;
    private String commentCreator;
}