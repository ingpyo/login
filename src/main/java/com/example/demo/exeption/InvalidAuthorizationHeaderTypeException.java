package com.example.demo.exeption;


public class InvalidAuthorizationHeaderTypeException extends RuntimeException {
    private static final String MESSAGE = "Authorization 헤더의 타입이 올바르지 않습니다.";


    public InvalidAuthorizationHeaderTypeException() {
        super(MESSAGE);
    }
}
