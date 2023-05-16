package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttractionMarkRepository extends JpaRepository<AttractionMark, Long> {

    Boolean existsByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
    Optional<AttractionMark> findByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
    void deleteByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);

}
