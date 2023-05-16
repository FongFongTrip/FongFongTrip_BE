package com.ssafy.fongfongtrip.domain.attraction.dto.request;

import jakarta.validation.constraints.NotNull;

public record RouteRequest(@NotNull Integer contentId) {
}
