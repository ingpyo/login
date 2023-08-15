package com.example.demo.domain.oauth;

import com.example.demo.domain.oauth.dto.UserInfo;
import com.example.demo.domain.SocialType;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Oauth2Clients {

    private final Map<SocialType, Oauth2Client> clients;

    public Oauth2Clients(Set<Oauth2Client> clients) {
        EnumMap<SocialType, Oauth2Client> mapping = new EnumMap<>(SocialType.class);
        clients.forEach(client -> mapping.put(client.getSocialType(), client));
        this.clients = mapping;
    }

    public String redirectUri(final SocialType socialType, final String redirectUri) {
        Oauth2Client client = getClient(socialType);
        return client.createRedirectUri(redirectUri);
    }

    public UserInfo requestUserInfo(final SocialType socialType, final String code, final String redirectUri) {
        Oauth2Client client = getClient(socialType);
        String accessToken = client.requestToken(code, redirectUri);
        return client.requestUserInfo(accessToken);
    }

    private Oauth2Client getClient(SocialType socialType) {
        return Optional.ofNullable(clients.get(socialType))
                .orElseThrow(() -> new IllegalArgumentException("해당 OAuth2 제공자는 지원되지 않습니다."));
    }
}