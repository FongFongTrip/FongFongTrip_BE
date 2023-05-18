package com.ssafy.fongfongtrip.domain.attraction.controller;

import com.ssafy.fongfongtrip.config.security.LoginUser;
import com.ssafy.fongfongtrip.domain.attraction.dto.request.AttractionInfoRequest;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionInfoResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionLikeResponse;
import com.ssafy.fongfongtrip.domain.attraction.dto.response.AttractionMarkResponse;
import com.ssafy.fongfongtrip.domain.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public ResponseEntity<List<AttractionInfoResponse>> attractionListByPaging(@PageableDefault(page = 0, size = 20, sort = "title", direction = Sort.Direction.DESC) Pageable pageable,
                                                                               @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findByPaging(pageable).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                attractionService.liked(attraction.getContentId(), loginUser.getMember().getId()),
                                attractionService.marked(attraction.getContentId(), loginUser.getMember().getId())))
                .toList());
    }

    @GetMapping("/contents/{contentTypeId}")
    public ResponseEntity<List<AttractionInfoResponse>> attractionListPagingByContentTypeId(@PathVariable Integer contentTypeId,
                                                                                            @PageableDefault(page = 0, size = 20, sort = "title", direction = Sort.Direction.DESC) Pageable pageable,
                                                                                            @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findPagingByContentTypeId(pageable, contentTypeId).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                attractionService.liked(attraction.getContentId(), loginUser.getMember().getId()),
                                attractionService.marked(attraction.getContentId(), loginUser.getMember().getId())))
                .toList());
    }


    @PostMapping
    public ResponseEntity<List<AttractionInfoResponse>> attractionListByCode(@RequestBody @Validated AttractionInfoRequest attractioninfoRequest,
                                                                             @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(attractionService.findAllByCode(attractioninfoRequest).stream()
                .map(attraction ->
                        AttractionInfoResponse.of(attraction,
                                attractionService.liked(attraction.getContentId(), loginUser.getMember().getId()),
                                attractionService.marked(attraction.getContentId(), loginUser.getMember().getId())))
                .toList());
    }

    @GetMapping("/{contentId}/like")
    public ResponseEntity<AttractionLikeResponse> like(@PathVariable Integer contentId,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.like(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionLikeResponse(true));
    }

    @DeleteMapping("/{contentId}/unlike")
    public ResponseEntity<AttractionLikeResponse> unlike(@PathVariable Integer contentId,
                                                    @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.unlike(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionLikeResponse(false));
    }

    @GetMapping("/{contentId}/mark")
    public ResponseEntity<AttractionMarkResponse> mark(@PathVariable Integer contentId,
                                                       @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.mark(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionMarkResponse(true));
    }

    @DeleteMapping("/{contentId}/unmark")
    public ResponseEntity<AttractionMarkResponse> unmark(@PathVariable Integer contentId,
                                                    @AuthenticationPrincipal LoginUser loginUser) {
        attractionService.unmark(contentId, loginUser.getMember().getId());
        return ResponseEntity.ok(new AttractionMarkResponse(false));
    }
}
