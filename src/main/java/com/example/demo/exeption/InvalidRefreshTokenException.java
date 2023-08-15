package com.example.demo.exeption;


public class InvalidRefreshTokenException extends RuntimeException {
    private static final String MESSAGE = "유효하지 않은 RefreshToken입니다. 다시 로그인을 진행하세요.";
    public InvalidRefreshTokenException() {
        super(MESSAGE);
    }
}
