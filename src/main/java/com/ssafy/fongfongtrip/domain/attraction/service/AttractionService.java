package com.ssafy.fongfongtrip.domain.attraction.service;

import com.ssafy.fongfongtrip.domain.attraction.dto.request.AttractionInfoRequest;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionInfo;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionLike;
import com.ssafy.fongfongtrip.domain.attraction.entity.AttractionMark;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionInfoRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionLikeRepository;
import com.ssafy.fongfongtrip.domain.attraction.repository.AttractionMarkRepository;
import com.ssafy.fongfongtrip.domain.member.entity.Member;
import com.ssafy.fongfongtrip.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionLikeRepository attractionLikeRepository;
    private final AttractionMarkRepository attractionMarkRepository;
    private final MemberRepository memberRepository;

    public List<AttractionInfo> findAll() {
        return attractionInfoRepository.findAll();
    }

    public Page<AttractionInfo> findBy(Pageable pageable) {
        return attractionInfoRepository.findByPaging(pageable);
    }

    public Page<AttractionInfo> findPagingByContentTypeId(Pageable pageable) {
        return attractionInfoRepository.findPagingByContentTypeId(pageable);
    }

    public List<AttractionInfo> findAllByCode(AttractionInfoRequest attractionInfoRequest) {
        return attractionInfoRepository.findByLocation(attractionInfoRequest.contentTypeId(),
                                                       attractionInfoRequest.sidoCode(),
                                                       attractionInfoRequest.gugunCode());
    }

    public AttractionInfo findByContentId(Integer contentId) {
        return attractionInfoRepository.findByContentId(contentId).orElseThrow(EntityNotFoundException::new);
    }

    public Boolean liked(Integer contentId, Long memberId) {
        return attractionLikeRepository.existsByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }

    public Boolean marked(Integer contentId, Long memberId) {
        return attractionMarkRepository.existsByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }

    @Transactional
    public AttractionLike like(Integer contentId, Long memberId) {
        Optional<AttractionLike> attractionLike = attractionLikeRepository.findByAttractionInfoContentIdAndMemberId(contentId, memberId);
        if (attractionLike.isPresent()) {
            return attractionLike.get();
        }
        AttractionInfo attractionInfo = attractionInfoRepository.findByContentId(contentId).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        return attractionLikeRepository.save(new AttractionLike(attractionInfo, member));
    }

    @Transactional
    public void unlike(Integer contentId, Long memberId) {
        attractionLikeRepository.deleteByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }

    @Transactional
    public AttractionMark mark(Integer contentId, Long memberId) {
        Optional<AttractionMark> attractionMark = attractionMarkRepository.findByAttractionInfoContentIdAndMemberId(contentId, memberId);
        if (attractionMark.isPresent()) {
            return attractionMark.get();
        }
        AttractionInfo attractionInfo = attractionInfoRepository.findByContentId(contentId).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        return attractionMarkRepository.save(new AttractionMark(attractionInfo, member));
    }

    @Transactional
    public void unmark(Integer contentId, Long memberId) {
        attractionMarkRepository.deleteByAttractionInfoContentIdAndMemberId(contentId, memberId);
    }
}

