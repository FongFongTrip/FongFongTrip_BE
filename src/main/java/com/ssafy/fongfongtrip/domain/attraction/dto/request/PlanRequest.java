package com.ssafy.fongfongtrip.domain.attraction.dto.request;

import java.util.List;

public record PlanRequest(List<RouteRequest> routeRequests) {
}
