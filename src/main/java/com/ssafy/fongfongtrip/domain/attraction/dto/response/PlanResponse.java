package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import com.ssafy.fongfongtrip.domain.member.dto.response.SimpleMemberResponse;

import java.util.List;

public record PlanResponse(Long id,
                           List<RouteResponse> routeResponses,
                           SimpleMemberResponse simpleMemberResponse) {
}
