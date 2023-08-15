package com.example.demo.exeption;


public class RefreshTokenNotFoundException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 토큰입니다. 다시 로그인을 진행하세요.";
    public RefreshTokenNotFoundException() {
        super(MESSAGE);
    }
}
