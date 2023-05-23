package com.ssafy.fongfongtrip.domain.attraction.service;

import com.ssafy.fongfongtrip.domain.attraction.dto.request.AttractionInfoRequest;
import com.ssafy.fongfongtrip.domain.attraction.dto.request.LocationRequest;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionDescription;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionLike;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionMark;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionDescriptionRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionInfoRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionLikeRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionMarkRepository;
import com.ssafy.fongfongtrip.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionDescriptionRepository attractionDescriptionRepository;
    private final AttractionLikeRepository attractionLikeRepository;
    private final AttractionMarkRepository attractionMarkRepository;
    private final MemberService memberService;

    public List<AttractionInfo> findAll() {
        return attractionInfoRepository.findAll();
    }

    public Page<AttractionInfo> findByPaging(Pageable pageable, Double latitude, Double longitude) {
        return attractionInfoRepository.findPagingAll(pageable, latitude, longitude);
    }

    public Page<AttractionInfo> findPagingByContentTypeId(Pageable pageable, Integer contentTypeId, Double latitude, Double longitude) {
        return attractionInfoRepository.findPagingByContentTypeId(pageable, contentTypeId, latitude, longitude);
    }
    public Page<AttractionInfo> findPagingByKeyword(Pageable pageable, String keyword) {
        return attractionInfoRepository.findPagingByKeyword(pageable, keyword);
    }
    public Page<AttractionInfo> findPagingByContentTypeIdAndKeyword(Pageable pageable, Integer contentTypeId, String keyword) {
        return attractionInfoRepository.findPagingByContentTypeIdAndKeyword(pageable, contentTypeId, keyword);
    }

    public List<AttractionInfo> findAllByCode(AttractionInfoRequest attractionInfoRequest) {
        return attractionInfoRepository.findByTypeAndLocation(attractionInfoRequest.contentTypeId(),
                                                       attractionInfoRequest.sidoCode(),
                                                       attractionInfoRequest.gugunCode());
    }

    public List<AttractionInfo> findAllByLocation(LocationRequest locationRequest) {
        return attractionInfoRepository.findByLocation(locationRequest.sidoCode(), locationRequest.gugunCode());
    }

    public AttractionDescription findByContentId(Integer contentId) {
        return attractionDescriptionRepository.findById(contentId).orElseThrow(EntityNotFoundException::new);
    }

    public Boolean liked(Integer contentId, Long memberId) {
        return attractionLikeRepository.existsByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }

    public Boolean marked(Integer contentId, Long memberId) {
        return attractionMarkRepository.existsByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }

    @Transactional
    public void like(Integer contentId, Long memberId) {
        attractionLikeRepository.findByAttractionInfoContentIdAndMemberId(contentId, memberId)
                .orElseGet(() -> attractionLikeRepository.save(new AttractionLike(
                        findByContentId(contentId),
                        memberService.findById(memberId))));
    }

    @Transactional
    public void unlike(Integer contentId, Long memberId) {
        attractionLikeRepository.deleteByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }

    @Transactional
    public void mark(Integer contentId, Long memberId) {
        attractionMarkRepository.findByAttractionInfoContentIdAndMemberId(contentId, memberId)
                .orElseGet(() -> attractionMarkRepository.save(new AttractionMark(
                        findByContentId(contentId),
                        memberService.findById(memberId))));
    }

    @Transactional
    public void unmark(Integer contentId, Long memberId) {
        attractionMarkRepository.deleteByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }
}

