package com.example.demo.presentation.dto;

import com.example.demo.domain.SocialType;

public record RedirectUriRequest(
        SocialType socialType,
        String redirectUri
) {
}
