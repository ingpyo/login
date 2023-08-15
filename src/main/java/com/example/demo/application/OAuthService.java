package com.example.demo.application;

import com.example.demo.domain.SocialType;
import com.example.demo.domain.oauth.dto.UserInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.oauth.Oauth2Clients;
import com.example.demo.presentation.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final Oauth2Clients oauth2Clients;
    private final MemberRepository memberRepository;

    public OAuthService(final Oauth2Clients oauth2Clients,final MemberRepository memberRepository) {
        this.oauth2Clients = oauth2Clients;
        this.memberRepository = memberRepository;
    }

    public String createAuthorizeRedirectUri(final SocialType socialType, final String redirectUri) {
        return oauth2Clients.redirectUri(socialType, redirectUri);
    }

    public LoginResponse login(final SocialType socialType,final String code,final String redirectUri) {
        final UserInfo userInfo = oauth2Clients.requestUserInfo(
                socialType,
                code,
                redirectUri
        );

        return memberRepository.findBySocialIdAndSocialType(userInfo.socialId(), userInfo.socialType())
                .map(LoginResponse::logIn)
                .orElseGet(() -> LoginResponse.signUp(signUp(userInfo)));
    }

    private Member signUp(final UserInfo userInfo) {
        return memberRepository.save(userInfo.toMember());
    }
}
