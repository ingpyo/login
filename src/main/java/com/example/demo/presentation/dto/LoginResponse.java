package com.example.demo.presentation.dto;

import com.example.demo.domain.member.Member;

public record LoginResponse(
        Long memberId,
        Boolean isNew
) {

    public static LoginResponse logIn(final Member member) {
        return new LoginResponse(
                member.getId(),
                false
        );
    }

    public static LoginResponse signUp(final Member member) {
        return new LoginResponse(
                member.getId(),
                true
        );
    }
}
