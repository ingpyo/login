package com.example.demo.application;

public record Tokens(
        Long memberId,
        String accessToken,
        String refreshToken
) {
}
