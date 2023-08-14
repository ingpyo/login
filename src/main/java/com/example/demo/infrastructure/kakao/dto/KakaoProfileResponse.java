package com.example.demo.infrastructure.kakao.dto;

import com.example.demo.domain.SocialType;
import com.example.demo.domain.oauth.dto.UserInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.example.demo.infrastructure.kakao.dto.KakaoProfileResponse.KakaoAccount.*;

public record KakaoProfileResponse(
        Long id,
        @JsonProperty("kakao_account") KakaoAccount kakaoAccount
) {

    public UserInfo toUserInfo() {
        final Profile profile = kakaoAccount.profile;
        return new UserInfo(
                id,
                SocialType.KAKAO,
                profile.nickname,
                profile.profileImageUrl
        );
    }

    public record KakaoAccount(
            Profile profile
    ) {

        public record Profile(
                String nickname,
                @JsonProperty("profile_image_url") String profileImageUrl
        ) {

        }
    }
}