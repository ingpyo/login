package com.example.demo.infrastructure.kakao;

import com.example.demo.infrastructure.kakao.dto.KakaoProfileResponse;
import com.example.demo.domain.oauth.dto.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoOauth2UserInfoClient {

    private static final String PROFILE_URL = "https://kapi.kakao.com/v2/user/me";
    private final WebClient webClient;

    public KakaoOauth2UserInfoClient() {
        this.webClient = WebClient.create();
    }

    public UserInfo request(final String accessToken) {
        final KakaoProfileResponse kakaoProfileResponse = webClient.get()
                .uri(PROFILE_URL)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoProfileResponse.class)
                .block();
        return kakaoProfileResponse.toUserInfo();
    }
}