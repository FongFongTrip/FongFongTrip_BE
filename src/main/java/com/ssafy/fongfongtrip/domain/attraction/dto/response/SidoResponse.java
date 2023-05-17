package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import com.ssafy.fongfongtrip.domain.attraction.entity.Sido;

public record SidoResponse(Integer sidoCode,
                           String sidoName) {

    public static SidoResponse of(Sido sido) {
        return new SidoResponse(sido.getSidoCode(), sido.getSidoName());
    }
}
