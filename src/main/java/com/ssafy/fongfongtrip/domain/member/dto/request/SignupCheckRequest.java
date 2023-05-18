package com.ssafy.fongfongtrip.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupCheckRequest(@NotBlank String loginId) {
}
