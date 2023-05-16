package com.ssafy.fongfongtrip.domain.attraction.dto.request;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AttractionInfoRequest(@NotNull Integer contentTypeId,
                                    @NotNull Integer sidoCode,
                                    @NotNull Integer gugunCode) {

    public static AttractionInfoRequest of(AttractionInfo attractionInfo) {
        return AttractionInfoRequest.builder()
                .contentTypeId(attractionInfo.getContentTypeId())
                .sidoCode(attractionInfo.getSidoCode())
                .gugunCode(attractionInfo.getGugunCode())
                .build();
    }
}
