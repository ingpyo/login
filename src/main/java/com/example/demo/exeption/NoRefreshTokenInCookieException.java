package com.example.demo.exeption;


public class NoRefreshTokenInCookieException extends RuntimeException {
    private static final String MESSAGE = "쿠키에 RefreshToken이 존재하지 않습니다.";
    public NoRefreshTokenInCookieException() {
        super(MESSAGE);
    }

}
