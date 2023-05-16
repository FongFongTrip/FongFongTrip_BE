package com.ssafy.fongfongtrip.domain.attraction.dto.response;

public record AttractionInfoResponse(Integer contentId,
                                     Integer contentTypeId,
                                     String title,
                                     String addr1,
                                     String addr2,
                                     String zipcode,
                                     String tel,
                                     String firstImage,
                                     String firstImage2,
                                     Integer readcount,
                                     Integer sidoCode,
                                     Integer gugunCode,
                                     Double latitude,
                                     Double longitude,
                                     String mlevel,
                                     Boolean liked,
                                     Boolean marked) {
}
