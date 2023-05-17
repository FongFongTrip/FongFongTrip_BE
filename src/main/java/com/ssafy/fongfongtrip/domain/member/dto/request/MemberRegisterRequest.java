package com.ssafy.fongfongtrip.domain.member.dto.request;

import com.ssafy.fongfongtrip.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record MemberRegisterRequest(@NotNull String loginId,
                                    @NotNull String password,
                                    @NotNull String nickname,
                                    @NotNull String email,
                                    @NotNull String phone) {

    public Member toMember(BCryptPasswordEncoder encoder) {
        return new Member(loginId, encoder.encode(password), nickname, email, phone);
    }
}
