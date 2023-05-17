package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.dto.request.AttractionInfoRequest;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {

    @Query(value = "select ai from AttractionInfo ai",
            countQuery = "select count(ai) from AttractionInfo ai")
    Page<AttractionInfo> findPagingAll(Pageable pageable);

    @Query(value = "select ai from AttractionInfo ai where ai.contentTypeId = :contentTypeId",
            countQuery = "select count(ai) from AttractionInfo ai")
    Page<AttractionInfo> findPagingByContentTypeId(Pageable pageable, @Param("contentTypeId") Integer contentTypeId);


    @Query("select ai from AttractionInfo ai " +
            "where ai.contentTypeId = :contentTypeId " +
            "and ai.sido.sidoCode = :sidoCode " +
            "and ai.gugun.gugunCode = :gugunCode")
    List<AttractionInfo> findByLocation(@Param("contentTypeId") Integer contentTypeId,
                                        @Param("sidoCode") Integer sidoCode,
                                        @Param("gugunCode") Integer gugunCode);

    Optional<AttractionInfo> findByContentId(Integer contentId);
}
