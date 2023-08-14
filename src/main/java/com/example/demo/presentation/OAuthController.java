package com.example.demo.presentation;

import com.example.demo.presentation.dto.LoginRequest;
import com.example.demo.presentation.dto.LoginResponse;
import com.example.demo.application.OAuthService;
import com.example.demo.presentation.dto.RedirectUriRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping
public class OAuthController {

    private final OAuthService oathService;


    @PostMapping("/login")
    public ResponseEntity<Void> oathRedirectUri(@RequestBody final RedirectUriRequest request) {
        final String authorizeRedirectUri = oathService.createAuthorizeRedirectUri(request);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, authorizeRedirectUri)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        final LoginResponse response = oathService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
