package com.ssafy.fongfongtrip.domain.attraction.repository;

import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Integer> {

    @Query(value = "select ai from AttractionInfo ai order by sqrt(power(abs(ai.latitude - :latitude), 2) + power(abs(ai.longitude - :longitude), 2)) ",
            countQuery = "select count(ai) from AttractionInfo ai")
    Page<AttractionInfo> findPagingAll(Pageable pageable, @Param("latitude") Double latitude, @Param("longitude") Double longitude);

    @Query(value = "select ai from AttractionInfo ai where ai.contentTypeId = :contentTypeId order by sqrt(power(abs(ai.latitude - :latitude), 2) + power(abs(ai.longitude - :longitude), 2)) ",
            countQuery = "select count(ai) from AttractionInfo ai")
    Page<AttractionInfo> findPagingByContentTypeId(Pageable pageable,
                                                   @Param("contentTypeId") Integer contentTypeId,
                                                   @Param("latitude") Double latitude,
                                                   @Param("longitude") Double longitude);


    @Query(value = "select ai from AttractionInfo ai where ai.title like concat('%', :keyword, '%') and ai.contentTypeId = :contentTypeId ",
            countQuery = "select count(ai) from AttractionInfo ai")
    Page<AttractionInfo> findPagingByContentTypeIdAndKeyword(Pageable pageable,
                                                   @Param("contentTypeId") Integer contentTypeId,
                                                   @Param("keyword") String keyword);

    @Query(value = "select ai from AttractionInfo ai where ai.title like concat('%', :keyword, '%') ",
            countQuery = "select count(ai) from AttractionInfo ai")
    Page<AttractionInfo> findPagingByKeyword(Pageable pageable, @Param("keyword") String keyword);

    @Query("select ai from AttractionInfo ai " +
            "where ai.contentTypeId = :contentTypeId " +
            "and ai.sido.sidoCode = :sidoCode " +
            "and ai.gugun.gugunCode = :gugunCode")
    List<AttractionInfo> findByTypeAndLocation(@Param("contentTypeId") Integer contentTypeId,
                                               @Param("sidoCode") Integer sidoCode,
                                               @Param("gugunCode") Integer gugunCode);

    @Query("select ai from AttractionInfo ai where ai.sido.sidoCode = :sidoCode and ai.gugun.gugunCode = :gugunCode")
    List<AttractionInfo> findByLocation( @Param("sidoCode") Integer sidoCode,
                                         @Param("gugunCode") Integer gugunCode);

    Optional<AttractionInfo> findByContentId(Integer contentId);
}
