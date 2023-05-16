package com.ssafy.fongfongtrip.domain.attraction.dto.request;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AttractionInfoRequest(@NotNull Integer contentTypeId,
                                    @NotNull Integer sidoCode,
                                    @NotNull Integer gugunCode) {
}
