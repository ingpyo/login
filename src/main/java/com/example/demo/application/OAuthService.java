package com.example.demo.application;

import com.example.demo.domain.SocialType;
import com.example.demo.domain.oauth.dto.UserInfo;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.oauth.Oauth2Clients;
import com.example.demo.domain.token.JwtTokenProvider;
import com.example.demo.domain.token.RefreshToken;
import com.example.demo.domain.token.TokenRepository;
import com.example.demo.presentation.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final Oauth2Clients oauth2Clients;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String createAuthorizeRedirectUri(final SocialType socialType, final String redirectUri) {
        return oauth2Clients.redirectUri(socialType, redirectUri);
    }

    public LoginResponse login(final SocialType socialType, final String code, final String redirectUri) {
        final UserInfo userInfo = oauth2Clients.requestUserInfo(
                socialType,
                code,
                redirectUri
        );

        return memberRepository.findBySocialIdAndSocialType(userInfo.socialId(), userInfo.socialType())
                .map(member -> LoginResponse.logIn(createTokens(member)))
                .orElseGet(() -> LoginResponse.signUp(createTokens(signUp(userInfo))));
    }

    private Member signUp(final UserInfo userInfo) {
        return memberRepository.save(userInfo.toMember());
    }

    private Tokens createTokens(final Member member) {
        final String accessToken = jwtTokenProvider.createAccessToken(member.getId());
        final String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        synchronizeRefreshToken(member, refreshToken);

        return new Tokens(member.getId(), accessToken, refreshToken);
    }

    private void synchronizeRefreshToken(final Member member, final String refreshToken) {
        tokenRepository.findByMemberId(member.getId())
                .ifPresentOrElse(
                        token -> token.update(refreshToken),
                        () -> tokenRepository.save(new RefreshToken(refreshToken, member))
                );
    }
}
