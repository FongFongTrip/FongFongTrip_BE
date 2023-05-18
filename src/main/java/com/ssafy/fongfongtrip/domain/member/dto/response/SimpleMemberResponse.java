package com.ssafy.fongfongtrip.domain.member.dto.response;

import com.ssafy.fongfongtrip.config.security.oauth.mapper.AuthProvider;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.entity.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record SimpleMemberResponse(Long id,
                                   String accountId,
                                   AuthProvider authProvider,
                                   String loginId,
                                   String password,
                                   String nickname,
                                   String email,
                                   String phone) {

    public static SimpleMemberResponse of(Member member) {
        return SimpleMemberResponse.builder()
                .id(member.getId())
                .accountId(member.getOauth2().getAccountId())
                .authProvider(member.getOauth2().getAuthProvider())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }
}
