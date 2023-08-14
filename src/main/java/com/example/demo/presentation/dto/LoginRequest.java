package com.example.demo.presentation.dto;

import com.example.demo.domain.SocialType;

public record LoginRequest(
        SocialType socialType,
        String redirect_uri,
        String authCode
) {
}
