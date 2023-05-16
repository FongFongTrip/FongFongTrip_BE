package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import java.time.LocalDateTime;

public record AttractionDetailResponse(Integer contentId,
                                       String cat1,
                                       String cat2,
                                       String cat3,
                                       String createdTime,
                                       String modifiedTime,
                                       String booktour) {
}
