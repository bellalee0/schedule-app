package com.example.scheduleapp.exception;

/**
 *  비밀번호 불일치 시 발생하는 예외 클래스
 */
public class IncorrectPassword extends RuntimeException {
    public IncorrectPassword(String message) {
        super(message);
    }
}
