package com.example.demo.exeption;


public class ExpiredRefreshTokenException extends RuntimeException {
    private static final String MESSAGE = "유효하지 않은 토큰입니다.";
    
    public ExpiredRefreshTokenException() {
        super(MESSAGE);
    }

}
