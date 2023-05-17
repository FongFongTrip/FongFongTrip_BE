package com.ssafy.fongfongtrip.domain.board.dto.request;

import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest(@NotNull String title,
                                @NotNull String content,
                                @NotNull Boolean isNotice) {
}
