package com.example.demo.presentation.dto;

import com.example.demo.application.Tokens;

public record LoginResponse(
        Long memberId,
        String accessToken,
        String refreshToken,
        Boolean isNew
) {

    public static LoginResponse logIn(final Tokens tokens) {
        return new LoginResponse(
                tokens.memberId(),
                tokens.accessToken(),
                tokens.refreshToken(),
                true
        );
    }

    public static LoginResponse signUp(final Tokens tokens) {
        return new LoginResponse(
                tokens.memberId(),
                tokens.accessToken(),
                tokens.refreshToken(),
                true
        );
    }
}
