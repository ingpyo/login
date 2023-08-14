package com.example.demo.domain.oauth.dto;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.SocialType;

public record UserInfo(
        Long socialId,
        SocialType socialType,
        String nickname,
        String profileImage
) {
    public Member toMember() {
        return new Member(
                socialId,
                socialType,
                nickname,
                profileImage
        );
    }
}
