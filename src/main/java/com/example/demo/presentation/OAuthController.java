package com.example.demo.presentation;

import com.example.demo.domain.SocialType;
import com.example.demo.presentation.dto.LoginRequest;
import com.example.demo.presentation.dto.LoginResponse;
import com.example.demo.application.OAuthService;
import com.example.demo.presentation.dto.RedirectUriRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OAuthController {

    private final OAuthService oathService;

    @PostMapping("/{socialType}")
    public ResponseEntity<Void> oathRedirectUri(@RequestBody final RedirectUriRequest request, HttpServletResponse response) throws IOException {
        final String authorizeRedirectUri = oathService.createAuthorizeRedirectUri(request);
        response.sendRedirect(authorizeRedirectUri);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{socialType}")
    ResponseEntity<LoginResponse> login(
            @PathVariable SocialType socialType,
            @RequestParam("code") String code
    ) {
        LoginResponse response = oathService.login(socialType, code);
        return ResponseEntity.ok(response);
    }
}
