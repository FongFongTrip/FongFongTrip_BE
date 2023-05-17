package com.ssafy.fongfongtrip.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record MemberLoginRequest(@NotNull String loginId,
                                 @NotNull String password) {
}
