package com.ssafy.fongfongtrip.domain.member.dto.response;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberResponse(Long id,
                             String loginId,
                             String password,
                             String nickname,
                             String email,
                             String phone) {

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }
}
