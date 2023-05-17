package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import com.ssafy.fongfongtrip.domain.attraction.entity.Gugun;

public record GugunResponse(Integer gugunCode,
                            String gugunName) {

    public static GugunResponse of(Gugun gugun) {
        return new GugunResponse(gugun.getGugunCode(), gugun.getGugunName());
    }
}
