package com.example.demo.infrastructure.kakao;

import com.example.demo.domain.oauth.Oauth2Client;
import com.example.demo.domain.SocialType;
import com.example.demo.domain.oauth.dto.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class KakaoOauth2Client implements Oauth2Client {

    private final KakaoOauth2TokenClient tokenClient;
    private final KakaoOauth2UserInfoClient userInfoClient;

    public KakaoOauth2Client(final KakaoOauth2TokenClient tokenClient,
                             final KakaoOauth2UserInfoClient userInfoClient
    ) {
        this.tokenClient = tokenClient;
        this.userInfoClient = userInfoClient;
    }

    @Override
    public String createRedirectUri(final String redirectUri) {
        return tokenClient.createRedirectUri(redirectUri);
    }

    @Override
    public String requestToken(final String authCode) {
        return tokenClient.request(authCode);
    }

    @Override
    public UserInfo requestUserInfo(final String accessToken) {
        return userInfoClient.request(accessToken);
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}