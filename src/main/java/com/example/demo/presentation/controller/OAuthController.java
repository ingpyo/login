package com.example.demo.presentation.controller;

import com.example.demo.domain.SocialType;
import com.example.demo.presentation.dto.LoginResponse;
import com.example.demo.application.OAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OAuthController {

    private final OAuthService oathService;

    @GetMapping("/{socialType}")
    public ResponseEntity<Void> oathRedirectUri(
            @PathVariable SocialType socialType,
            @RequestParam final String redirectUri,
            HttpServletResponse response
    ) throws IOException {
        final String authorizeRedirectUri = oathService.createAuthorizeRedirectUri(socialType, redirectUri);
        response.sendRedirect(authorizeRedirectUri);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{socialType}")
    ResponseEntity<LoginResponse> login(
            @PathVariable SocialType socialType,
            @RequestParam("code") String code,
            @RequestParam("redirectUri") String redirectUri
    ) {
        LoginResponse response = oathService.login(socialType, code, redirectUri);
        return ResponseEntity.ok(response);
    }
}
