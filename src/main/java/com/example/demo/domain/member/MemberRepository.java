package com.example.demo.domain.member;

import com.example.demo.domain.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findBySocialIdAndSocialType(final Long socialId,final SocialType socialType);
}
