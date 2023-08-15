package com.example.demo.domain.member;

import com.example.demo.domain.SocialType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long socialId;
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;
    private String nickname;
    private String profileImage;


    public Member(final Long socialId, final SocialType socialType, final String nickname, final String profileImage) {
        this(null, socialId, socialType, nickname, profileImage);
    }

    public Member(final Long id, final Long socialId, final SocialType socialType, final String nickname, final String profileImage) {
        this.id = id;
        this.socialId = socialId;
        this.socialType = socialType;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Member() {

    }
}
