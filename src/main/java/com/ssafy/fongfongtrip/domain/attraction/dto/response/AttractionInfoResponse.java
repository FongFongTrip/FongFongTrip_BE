package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import lombok.Builder;

@Builder
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

    public static AttractionInfoResponse of(AttractionInfo attractionInfo, Boolean liked, Boolean marked) {
        return AttractionInfoResponse.builder()
                .contentId(attractionInfo.getContentId())
                .contentTypeId(attractionInfo.getContentTypeId())
                .title(attractionInfo.getTitle())
                .addr1(attractionInfo.getAddr1())
                .addr2(attractionInfo.getAddr2())
                .zipcode(attractionInfo.getZipcode())
                .tel(attractionInfo.getTel())
                .firstImage(attractionInfo.getFirstImage())
                .firstImage2(attractionInfo.getFirstImage2())
                .readcount(attractionInfo.getReadcount())
                .sidoCode(attractionInfo.getSido().getSidoCode())
                .gugunCode(attractionInfo.getGugun().getGugunCode())
                .latitude(attractionInfo.getLatitude())
                .longitude(attractionInfo.getLongitude())
                .mlevel(attractionInfo.getMlevel())
                .liked(liked)
                .marked(marked)
                .build();
    }
}
