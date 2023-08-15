package com.example.demo.exeption;


public class AuthorizationHeaderNotFoundException extends RuntimeException {
    private static final String MESSAGE = "header가 존재하지 않습니다.";
    public AuthorizationHeaderNotFoundException() {
        super(MESSAGE);
    }
}
