package com.example.scheduleapp.exception;

/**
 *  존재하지 않는 일정ID 조회 시 발생하는 예외 클래스
 */
public class NotFoundSchedule extends RuntimeException {
    public NotFoundSchedule(String message) {
        super(message);
    }
}
