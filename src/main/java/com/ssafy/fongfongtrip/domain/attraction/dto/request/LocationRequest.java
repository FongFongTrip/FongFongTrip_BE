package com.ssafy.fongfongtrip.domain.attraction.dto.request;

import jakarta.validation.constraints.NotNull;

public record LocationRequest(@NotNull Integer sidoCode,
                              @NotNull Integer gugunCode) {
}
