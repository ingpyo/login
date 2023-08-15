package com.example.demo.infrastructure.kakao;

import com.example.demo.infrastructure.kakao.dto.KakaoTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class KakaoOauth2TokenClient {

    private static final String AUTHORIZE_URL = "https://kauth.kakao.com/oauth/authorize";
    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String GRANT_TYPE = "authorization_code";
    private static final String RESPONSE_TYPE = "code";

    private final String kakaoClientId;
    private final String kakaoClientSecret;
    private final String kakaoRedirectUri;
    private final WebClient webClient;

    public KakaoOauth2TokenClient(
            @Value("${kakao.client-id}") String kakaoClientId,
            @Value("${kakao.client-secret}") String kakaoClientSecret,
            @Value("${kakao.redirect-uri}")String kakaoRedirectUri
    ) {
        this.kakaoClientId = kakaoClientId;
        this.kakaoClientSecret = kakaoClientSecret;
        this.kakaoRedirectUri = kakaoRedirectUri;
        this.webClient = WebClient.create();
    }

    public String createRedirectUri(final String redirectUri) {
        return UriComponentsBuilder.fromUriString(AUTHORIZE_URL)
                .queryParam("client_id", kakaoClientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", RESPONSE_TYPE)
                .build()
                .toUriString();
    }

    public String request(final String authCode) {
        final BodyInserters.FormInserter<String> bodyForm = BodyInserters.fromFormData("grant_type", GRANT_TYPE)
                .with("client_id", kakaoClientId)
                .with("redirect_uri", kakaoRedirectUri)
                .with("code", authCode)
                .with("client_secret", kakaoClientSecret);

        final KakaoTokenResponse kakaoTokenResponse = webClient.post()
                .uri(TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(bodyForm)
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .block();
        return Objects.requireNonNull(kakaoTokenResponse).access_token();
    }
}
