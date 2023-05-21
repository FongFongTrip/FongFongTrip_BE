package com.ssafy.fongfongtrip.domain.attraction.dto.response;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionDescription;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import lombok.Builder;

@Builder
public record AttractionDescriptionResponse(Integer contentId,
                                            String homepage,
                                            String overview,
                                            String telname,
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

    public static AttractionDescriptionResponse of(AttractionDescription attractionDescription, Boolean liked, Boolean marked) {
        return AttractionDescriptionResponse.builder()
                .contentId(attractionDescription.getContentId())
                .homepage(attractionDescription.getHomepage())
                .overview(attractionDescription.getOverview())
                .telname(attractionDescription.getTelname())
                .contentTypeId(attractionDescription.getContentTypeId())
                .title(attractionDescription.getTitle())
                .addr1(attractionDescription.getAddr1())
                .addr2(attractionDescription.getAddr2())
                .zipcode(attractionDescription.getZipcode())
                .tel(attractionDescription.getTel())
                .firstImage(attractionDescription.getFirstImage())
                .firstImage2(attractionDescription.getFirstImage2())
                .readcount(attractionDescription.getReadcount())
                .sidoCode(attractionDescription.getSido().getSidoCode())
                .gugunCode(attractionDescription.getGugun().getGugunCode())
                .latitude(attractionDescription.getLatitude())
                .longitude(attractionDescription.getLongitude())
                .mlevel(attractionDescription.getMlevel())
                .liked(liked)
                .marked(marked)
                .build();
    }
}
