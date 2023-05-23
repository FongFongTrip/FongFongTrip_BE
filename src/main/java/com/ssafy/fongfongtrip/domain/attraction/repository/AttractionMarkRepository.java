package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionMark;
import com.ssafy.fongfongtrip.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttractionMarkRepository extends JpaRepository<AttractionMark, Long> {

    Boolean existsByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
    Optional<AttractionMark> findByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);
    void deleteByAttractionInfoContentIdAndMemberId(Integer contentId, Long memberId);

    @Query(value = "select ai from AttractionInfo ai, AttractionMark am where ai.contentId = am.attractionInfo.contentId and am.member.id = :memberId ",
            countQuery = "select count(ai) from AttractionInfo ai")
    List<AttractionInfo> findMarkByMemberId(@Param("memberId") Long memberId);
}
