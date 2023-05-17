package com.ssafy.fongfongtrip.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record AuthRequest(@NotNull String authToken) {
}
