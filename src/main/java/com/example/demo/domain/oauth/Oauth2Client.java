package com.example.demo.domain.oauth;

import com.example.demo.domain.oauth.dto.UserInfo;
import com.example.demo.domain.SocialType;

public interface Oauth2Client {
    String createRedirectUri(final String redirectUri);

    String requestToken(final String authCode);

    UserInfo requestUserInfo(final String accessToken);

    SocialType getSocialType();
}
