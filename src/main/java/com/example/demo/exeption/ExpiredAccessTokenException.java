package com.example.demo.exeption;


public class ExpiredAccessTokenException extends RuntimeException {
    private static final String MESSAGE = "유효하지 않은 토큰입니다.";

    public ExpiredAccessTokenException() {
        super(MESSAGE);
    }
}
