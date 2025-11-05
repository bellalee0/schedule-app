package com.example.scheduleapp.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String comment;
    private String commentCreator;
    private Long commentPassword;
}
