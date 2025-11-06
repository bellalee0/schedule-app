package com.example.scheduleapp.dto;

import lombok.Getter;

/**
 *  댓글 생성 요청 처리 DTO (댓글, 작성자, 비밀번호)
 */
@Getter
public class CreateCommentRequest {
    private String comment;
    private String commentCreator;
    private Long commentPassword;
}
