package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttractionLikeRepository extends JpaRepository<AttractionLike, Long> {

    Boolean existsByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
    Boolean existsByAttractionInfoAndMemberId(AttractionInfo attractionInfo, Long memberId);
    Optional<AttractionLike> findByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
    void deleteByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
}
