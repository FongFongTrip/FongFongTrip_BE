package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import java.util.List;

public record PlanResponse(Long id,
                           List<RouteResponse> routeResponses) {
}
