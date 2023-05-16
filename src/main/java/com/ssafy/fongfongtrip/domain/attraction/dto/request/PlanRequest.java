package com.ssafy.fongfongtrip.domain.attraction.dto.request;

import com.ssafy.fongfongtrip.domain.attraction.entity.Plan;
import com.ssafy.fongfongtrip.domain.attraction.entity.Route;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public record PlanRequest(@NotNull List<RouteRequest> routeRequests) {
}
