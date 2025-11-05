package com.example.scheduleapp.exception;

public class NotFoundSchedule extends RuntimeException {
    public NotFoundSchedule(String message) {
        super(message);
    }
}
