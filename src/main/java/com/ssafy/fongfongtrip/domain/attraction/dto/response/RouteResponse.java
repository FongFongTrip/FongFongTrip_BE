package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import com.ssafy.fongfongtrip.domain.attraction.entity.Route;

public record RouteResponse(Long id,
                            Long routeOrder,
                            AttractionInfoResponse attractionInfoResponse) {

    public static RouteResponse of(Route route, AttractionInfoResponse attractionInfoResponse) {
        return new RouteResponse(route.getId(), route.getRouteOrder(), attractionInfoResponse);
    }
}
