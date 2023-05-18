package com.ssafy.fongfongtrip.domain.board.dto.request;

import jakarta.validation.constraints.NotNull;

public record BoardSearchRequest(@NotNull String category,
                                 @NotNull String keyword) {
}
