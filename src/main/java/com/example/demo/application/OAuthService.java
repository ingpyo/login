package com.example.demo.application;

import com.example.demo.domain.oauth.dto.UserInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.oauth.Oauth2Clients;
import com.example.demo.presentation.dto.LoginRequest;
import com.example.demo.presentation.dto.LoginResponse;
import com.example.demo.presentation.dto.RedirectUriRequest;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final Oauth2Clients oauth2Clients;
    private final MemberRepository memberRepository;

    public OAuthService(final Oauth2Clients oauth2Clients,final MemberRepository memberRepository) {
        this.oauth2Clients = oauth2Clients;
        this.memberRepository = memberRepository;
    }

    public String createAuthorizeRedirectUri(final RedirectUriRequest request) {
        return oauth2Clients.redirectUri(request.socialType(), request.redirectUri());
    }

    public LoginResponse login(final LoginRequest request) {
        final UserInfo userInfo = oauth2Clients.requestUserInfo(
                request.socialType(),
                request.redirect_uri(),
                request.authCode()
        );

        return memberRepository.findBySocialIdAndSocialType(userInfo.socialId(), userInfo.socialType())
                .map(LoginResponse::logIn)
                .orElseGet(() -> LoginResponse.signUp(signUp(userInfo)));
    }

    private Member signUp(final UserInfo userInfo) {
        return memberRepository.save(userInfo.toMember());
    }
}
