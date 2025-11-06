package com.example.scheduleapp.exception;

/**
 *  존재하지 않는 댓글ID 조회 시 발생하는 예외 클래스
 */
public class NotFoundComment extends RuntimeException {
    public NotFoundComment(String message) {
        super(message);
    }
}