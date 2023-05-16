package com.ssafy.fongfongtrip.domain.attraction.dto.response;

public record RouteResponse(Long id,
                            Long order,
                            AttractionInfoResponse attractionInfoResponse,
                            Long planId) {
}
